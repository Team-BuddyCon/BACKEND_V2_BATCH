package yapp.buddycon.infra.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import yapp.buddycon.infra.entity.Gifticon;

public interface GifticonRepository extends JpaRepository<Gifticon, Long> {

  List<Gifticon> findAllByUserIdAndExpireDateAndUsedIsFalse(long userId, LocalDate expireDate);

}
