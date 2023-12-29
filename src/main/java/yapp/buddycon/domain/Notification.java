package yapp.buddycon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import yapp.buddycon.domain.code.NotificationPushStatus;

@Getter
@Entity
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "notification_id")
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "notification_push_status", nullable = false)
  private NotificationPushStatus notificationPushStatus;

  public static Notification create() {
    Notification notification = new Notification();
    notification.notificationPushStatus = NotificationPushStatus.READY;
    return notification;
  }

}
