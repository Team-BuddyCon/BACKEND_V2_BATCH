package yapp.buddycon.config;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yapp.buddycon.domain.Gifticon;

@RequiredArgsConstructor
@Configuration
public class NotificationBatchConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final DataSource dataSource;

  private final int CHUNK_SIZE = 10;

  @Bean
  public Job createGifticonExpirationAlertNotiJob() throws Exception {
    // Job 생성
    return jobBuilderFactory.get("createGifticonExpirationAlertNotiJob")
        .start(createGifticonExpirationAlertNotiStep())
        .incrementer(new RunIdIncrementer())  // TODO 삭제
        .build();
  }

  @Bean
  public Step createGifticonExpirationAlertNotiStep() throws Exception {
    return stepBuilderFactory.get("createGifticonExpirationAlertNotiStep")
        .<Gifticon, Gifticon>chunk(CHUNK_SIZE)
        .reader(jdbcPagingItemReader())
        .writer(items -> {
          items.forEach(item -> System.out.println(item.toString()));
          System.out.println("--------------");
        })
        .build();
  }

  @Bean
  public JdbcPagingItemReader<Gifticon> jdbcPagingItemReader() throws Exception {
    HashMap<String, Object> parameters = new HashMap<>();
    parameters.put("today", LocalDate.now());

    return new JdbcPagingItemReaderBuilder<Gifticon>()
        .name("jdbcPagingItemReader")
        .dataSource(dataSource)
        .beanRowMapper(Gifticon.class)
        .pageSize(CHUNK_SIZE)
        .fetchSize(CHUNK_SIZE)
        .queryProvider(createQueryProvider())
        .parameterValues(parameters)
        .build();
  }

  @Bean
  public PagingQueryProvider createQueryProvider() throws Exception {
    SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
    queryProvider.setDataSource(dataSource);
    queryProvider.setSelectClause("g.gifticon_id, g.name, g.expire_date, g.user_id");
    queryProvider.setFromClause("from gifticon g INNER JOIN notification_setting ns ON ns.user_id = g.user_id");
    queryProvider.setWhereClause("""
        WHERE g.used = false
          AND ns.activated = true
          AND (
            (ns.the_day = true AND g.expire_date = DATE(:today))
            OR (ns.one_day_before = true AND g.expire_date = DATE(DATE_ADD(:today, INTERVAL + 1 DAY)))
            OR (ns.three_days_before = true AND g.expire_date = DATE(DATE_ADD(:today, INTERVAL + 3 DAY)))
            OR (ns.seven_days_before = true AND g.expire_date = DATE(DATE_ADD(:today, INTERVAL + 7 DAY)))
            OR (ns.fourteen_days_before = true AND g.expire_date = DATE(DATE_ADD(:today, INTERVAL + 14 DAY)))
          )
        """);

    Map<String, Order> sortKeys = new HashMap<>(1);
    sortKeys.put("g.gifticon_id", Order.ASCENDING);
    queryProvider.setSortKeys(sortKeys);

    return queryProvider.getObject();
  }
}
