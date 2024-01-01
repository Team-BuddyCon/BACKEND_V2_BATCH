package yapp.buddycon.scheduler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import yapp.buddycon.batch.BatchConfig;

@RequiredArgsConstructor
@Component
public class SchedulerConfig {

  private final JobLauncher jobLauncher;
  private final BatchConfig notificationBatchConfig;

  @Scheduled(cron = "0 0 1 * * ?")
  public void startCreateGifticonAlertJob() {
    // 파라미터 설정
    LocalDate today = LocalDate.now();
    String todayString = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    Map<String, JobParameter> parameterMap = Map.of("date", new JobParameter(todayString));
    JobParameters jobParameters = new JobParameters(parameterMap);

    // 배치
    try {
      jobLauncher.run(notificationBatchConfig.createGifticonAlertJob(), jobParameters);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
