package yapp.buddycon.batch;

import java.time.LocalDate;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yapp.buddycon.domain.Gifticon;

@RequiredArgsConstructor
@Configuration
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final EntityManagerFactory entityManagerFactory;

  private final int CHUNK_SIZE = 10;

  @Bean
  public Job createGifticonAlertJob() throws Exception {
    return jobBuilderFactory.get("createGifticonAlertJob")
        .start(createGifticonAlertStep())
        .build();
  }

  @Bean
  public Step createGifticonAlertStep() throws Exception {
    return stepBuilderFactory.get("createGifticonAlertStep")
        .<Gifticon, Gifticon>chunk(CHUNK_SIZE)
        .reader(jpaPagingItemReaderForCreateGifticonAlertStep())
        .writer(new CustomItemWriterForCreateGifticonAlertStep(entityManagerFactory))
        .build();
  }

  @Bean
  public JpaPagingItemReader<Gifticon> jpaPagingItemReaderForCreateGifticonAlertStep() {
    return new JpaPagingItemReaderBuilder<Gifticon>()
        .name("jpaPagingItemReaderForCreateGifticonAlertStep")
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

}
