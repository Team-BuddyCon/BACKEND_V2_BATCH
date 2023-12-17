package yapp.buddycon.infra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import yapp.buddycon.infra.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

  @Modifying
  @Query(value = "update Notification n set n.notificationPushStatus = 'SENT' where n.id in :idList")
  void updateAllById(List<Long> idList);

}
