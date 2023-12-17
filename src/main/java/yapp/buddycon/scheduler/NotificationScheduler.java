package yapp.buddycon.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import yapp.buddycon.service.NotificationService;

@RequiredArgsConstructor
@Component
public class NotificationScheduler {

  private final NotificationService notificationService;

//  @Scheduled(cron = "0/3 * * * * ?")
//  public void sendFourteenNotification() {
//    notificationService.createNotifications(14);
//  }

  @Scheduled(cron = "0 0 1 * * ?")
  public void createNotificationBeforeFourteenDays() {
    notificationService.createNotifications(14);
  }

  @Scheduled(cron = "0 10 1 * * ?")
  public void createNotificationBeforeSevenDays() {
    notificationService.createNotifications(7);
  }

  @Scheduled(cron = "0 20 1 * * ?")
  public void createNotificationBeforeThreeDays() {
    notificationService.createNotifications(3);
  }

  @Scheduled(cron = "0 30 1 * * ?")
  public void createNotificationBeforeOneDay() {
    notificationService.createNotifications(1);
  }

  @Scheduled(cron = "0 40 1 * * ?")
  public void createNotificationOnTheDay() {
    notificationService.createNotifications(0);
  }

}
