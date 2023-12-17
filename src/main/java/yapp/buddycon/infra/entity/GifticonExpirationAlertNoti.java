package yapp.buddycon.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "gifticon_expiration_alert_noti")
public class GifticonExpirationAlertNoti extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gifticon_expiration_alert_noti_id")
  private Long id;
  @Column(name = "notification_id", nullable = false)
  private Long notificationId;
  @Column(name = "gifticon_id", nullable = false)
  private Long gifticonId;
  @Column(name = "days_left", nullable = false)
  private Integer daysLeft;

  @Builder
  public GifticonExpirationAlertNoti(Long id, Long notificationId, Long gifticonId, Integer daysLeft) {
    this.id = id;
    this.notificationId = notificationId;
    this.gifticonId = gifticonId;
    this.daysLeft = daysLeft;
  }
}

