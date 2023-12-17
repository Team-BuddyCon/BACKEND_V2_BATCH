package yapp.buddycon.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class NotificationSetting extends BaseEntity {

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
