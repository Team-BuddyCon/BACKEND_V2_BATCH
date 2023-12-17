package yapp.buddycon.infra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import yapp.buddycon.infra.entity.NotificationSetting;

public interface NotificationSettingRepository extends JpaRepository<NotificationSetting, Long> {

  List<NotificationSetting> findAllByActivatedIsTrueAndFourteenDaysBeforeIsTrue();

}
