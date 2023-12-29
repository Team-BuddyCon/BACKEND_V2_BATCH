package yapp.buddycon.config;

import java.time.LocalDate;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yapp.buddycon.batch.CustomItemWriter;
import yapp.buddycon.domain.Gifticon;

@RequiredArgsConstructor
@Configuration
public class NotificationBatchConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final EntityManagerFactory entityManagerFactory;

  private final int CHUNK_SIZE = 10;

  @Bean
  public Job createGifticonExpirationAlertNotiJob() throws Exception {
    return jobBuilderFactory.get("createGifticonExpirationAlertNotiJob")
        .start(createGifticonExpirationAlertNotiStep())
        .incrementer(new RunIdIncrementer())  // TODO 삭제
        .build();
  }

  @Bean
  public Step createGifticonExpirationAlertNotiStep() throws Exception {
    return stepBuilderFactory.get("createGifticonExpirationAlertNotiStep")
        .<Gifticon, Gifticon>chunk(CHUNK_SIZE)
        .reader(jpaPagingItemReader())
        .writer(customWriter())
        .build();
  }

  @Bean
  public JpaPagingItemReader<Gifticon> jpaPagingItemReader() {
    return new JpaPagingItemReaderBuilder<Gifticon>()
        .name("jpaPagingItemReader")
        .entityManagerFactory(entityManagerFactory)
        .pageSize(CHUNK_SIZE)
        .queryString("""
                SELECT g
                FROM Gifticon g
                    INNER JOIN NotificationSetting ns
                        ON ns.userId = g.userId
                WHERE g.used = false
                  AND ns.activated = true
                  AND (
                    (ns.theDay = true AND g.expireDate = DATE(:today))
                    OR (ns.oneDayBefore = true AND g.expireDate = DATE(:oneDayAfter))
                    OR (ns.threeDaysBefore = true AND g.expireDate = DATE(:threeDaysAfter))
                    OR (ns.sevenDaysBefore = true AND g.expireDate = DATE(:sevenDaysAfter))
                    OR (ns.fourteenDaysBefore = true AND g.expireDate = DATE(:fourteenDaysAfter))
                  )
                ORDER BY g.id ASC
            """)
        .parameterValues(Map.of(
            "today", LocalDate.now(),
            "oneDayAfter", LocalDate.now().plusDays(1),
            "threeDaysAfter", LocalDate.now().plusDays(3),
            "sevenDaysAfter", LocalDate.now().plusDays(7),
            "fourteenDaysAfter", LocalDate.now().plusDays(14)
        ))
        .build();
  }

  public ItemWriter<Gifticon> customWriter() {
    return new CustomItemWriter(entityManagerFactory);
  }

}
