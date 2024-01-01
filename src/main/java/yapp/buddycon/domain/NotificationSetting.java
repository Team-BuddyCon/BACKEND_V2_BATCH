package yapp.buddycon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class NotificationSetting {

  @Id
  @Column(name = "notification_setting_id")
  private Long id;

  private Long userId;
  private boolean activated;
  private boolean fourteenDaysBefore;
  private boolean sevenDaysBefore;
  private boolean threeDaysBefore;
  private boolean oneDayBefore;
  private boolean theDay;

}
