package yapp.buddycon.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TimeZone;

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

  @Scheduled(cron = "0/10 * * * * ?"/*, zone = "Asia/Seoul"*/)
  public void startCreateGifticonAlertJob() {
    // 파라미터 설정
//    LocalDate today = LocalDate.now();
//    String todayString = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    String nowStr = LocalDateTime.now().toString();

    Map<String, JobParameter> parameterMap = Map.of("date", new JobParameter(nowStr));
    JobParameters jobParameters = new JobParameters(parameterMap);

    // 배치
    try {
      jobLauncher.run(notificationBatchConfig.createGifticonAlertJob(), jobParameters);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
