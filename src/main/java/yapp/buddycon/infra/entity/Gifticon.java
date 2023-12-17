package yapp.buddycon.infra.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Gifticon extends BaseEntity {

  @Id
  @Column(name = "gifticon_id")
  private Long id;
  private Long userId;
  private String name;
  private LocalDate expireDate;
  private boolean used;

}
