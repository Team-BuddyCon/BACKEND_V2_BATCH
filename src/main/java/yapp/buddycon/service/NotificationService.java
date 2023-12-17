package yapp.buddycon.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.infra.entity.Gifticon;
import yapp.buddycon.infra.entity.GifticonExpirationAlertNoti;
import yapp.buddycon.infra.entity.Notification;
import yapp.buddycon.infra.entity.NotificationSetting;
import yapp.buddycon.infra.repository.GifticonExpirationAlertNotiRepository;
import yapp.buddycon.infra.repository.GifticonRepository;
import yapp.buddycon.infra.repository.NotificationRepository;
import yapp.buddycon.infra.repository.NotificationSettingRepository;
import yapp.buddycon.service.dto.GifticonExpirationAlertNotificationDto;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationService {

  private final GifticonRepository gifticonRepository;
  private final NotificationSettingRepository notificationSettingRepository;
  private final GifticonExpirationAlertNotiRepository gifticonExpirationAlertNotiRepository;
  private final NotificationRepository notificationRepository;

  @Transactional
  public void createNotifications(int daysBefore) {
    LocalDate today = LocalDate.now();

    // daysBefore 알림 설정이 활성화된 유저 목록 조회
    List<NotificationSetting> notificationSettingList = notificationSettingRepository
        .findAllByActivatedIsTrueAndFourteenDaysBeforeIsTrue();

    for (NotificationSetting ns : notificationSettingList) {
      long userId = ns.getUserId();

      // daysBefore 후 만료 예정인 기프티콘 목록 조회
      List<Gifticon> gifticonList = gifticonRepository
          .findAllByUserIdAndExpireDateAndUsedIsFalse(userId, today.plusDays(daysBefore));

      for (Gifticon gifticon : gifticonList) {
        // 기프티콘 만료 예정 알림 생성
        Notification savedNotification = notificationRepository.save(
            Notification
                .builder()
                .build()
        );

        gifticonExpirationAlertNotiRepository.save(
            GifticonExpirationAlertNoti.builder()
                .notificationId(savedNotification.getId())
                .gifticonId(gifticon.getId())
                .daysLeft(daysBefore)
                .build()
        );
      }
    }
  }

  @Transactional
  public void sendNotificationPush() {
    List<GifticonExpirationAlertNotificationDto> notificationList =
        gifticonExpirationAlertNotiRepository.findAllGifticonAlertNotificationForPush();

    List<Long> idList = new ArrayList<>();
    for (GifticonExpirationAlertNotificationDto n : notificationList) {
      try {
        // TODO 푸시 전송

        idList.add(n.notificiationId());

      } catch (Exception e) {
        log.error("푸시 전송 실패: {}", e.getMessage());
      }
    }

    notificationRepository.updateAllById(idList);
  }

}
