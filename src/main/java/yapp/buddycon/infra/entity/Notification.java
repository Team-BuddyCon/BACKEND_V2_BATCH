package yapp.buddycon.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yapp.buddycon.infra.entity.code.NotificationPushStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notification extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "notification_id")
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "notification_push_status", nullable = false)
  private NotificationPushStatus notificationPushStatus;

  @Builder
  public Notification(Long id, NotificationPushStatus notificationPushStatus) {
    this.id = id;
    this.notificationPushStatus = notificationPushStatus;
  }
}
