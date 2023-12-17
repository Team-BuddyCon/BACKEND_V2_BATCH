package yapp.buddycon.infra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yapp.buddycon.infra.entity.GifticonExpirationAlertNoti;
import yapp.buddycon.service.dto.GifticonExpirationAlertNotificationDto;

public interface GifticonExpirationAlertNotiRepository extends
    JpaRepository<GifticonExpirationAlertNoti, Long> {

  @Query(
      value =
          """
            SELECT
              new yapp.buddycon.service.dto.GifticonExpirationAlertNotificationDto(
              gen.id,
              gen.daysLeft,
              n.id,
              n.notificationPushStatus,
              g.id,
              g.name,
              u.id,
              u.nickname                  
              )
            FROM GifticonExpirationAlertNoti gen
              INNER JOIN Notification n
                ON n.id = gen.notificationId
              INNER JOIN Gifticon g
                ON g.id = gen.gifticonId
              INNER JOIN User u
                ON u.id = g.userId
              WHERE n.notificationPushStatus = 'READY'
          """
  )
  List<GifticonExpirationAlertNotificationDto> findAllGifticonAlertNotificationForPush();

}
