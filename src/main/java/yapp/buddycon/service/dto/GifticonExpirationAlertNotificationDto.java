package yapp.buddycon.service.dto;

import yapp.buddycon.infra.entity.code.NotificationPushStatus;

public record GifticonExpirationAlertNotificationDto(
    Long gifticonExpirationAlertNotiId,
    Integer daysLeft,

    Long notificiationId,
    NotificationPushStatus notificationPushStatus,

    Long gifticonId,
    String gifticonName,

    Long userId,
    String userNickName
) {

}
