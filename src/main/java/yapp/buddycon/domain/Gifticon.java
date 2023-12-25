package yapp.buddycon.domain;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Gifticon {
  Long gifticonId;
  String name;
  LocalDate expireDate;
  Long userId;
}
