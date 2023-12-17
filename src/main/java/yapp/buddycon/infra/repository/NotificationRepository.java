package yapp.buddycon.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yapp.buddycon.infra.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
