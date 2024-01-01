package yapp.buddycon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GifticonExpirationAlertNoti {

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

  public static GifticonExpirationAlertNoti create(long notificationId, long gifticonId, int daysLeft) {
    GifticonExpirationAlertNoti gifticonExpirationAlertNoti = new GifticonExpirationAlertNoti();
    gifticonExpirationAlertNoti.notificationId = notificationId;
    gifticonExpirationAlertNoti.gifticonId = gifticonId;
    gifticonExpirationAlertNoti.daysLeft = daysLeft;
    return gifticonExpirationAlertNoti;
  }
}
