package yapp.buddycon.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@ToString // TODO 삭제
@Getter
@Entity
public class Gifticon {

  @Id
  @Column(name = "gifticon_id")
  private Long id;

  private Long userId;
  private String name;
  private LocalDate expireDate;
  private boolean used;
}
