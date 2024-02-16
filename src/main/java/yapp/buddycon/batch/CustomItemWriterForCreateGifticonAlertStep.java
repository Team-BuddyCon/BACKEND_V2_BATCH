package yapp.buddycon.batch;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import yapp.buddycon.domain.Gifticon;
import yapp.buddycon.domain.GifticonExpirationAlertNoti;
import yapp.buddycon.domain.Notification;

@Slf4j
@RequiredArgsConstructor
public class CustomItemWriterForCreateGifticonAlertStep implements ItemWriter<Gifticon> {

  private final EntityManagerFactory emf;

  @Override
  public void write(List<? extends Gifticon> items) throws Exception {
    EntityManager em = EntityManagerFactoryUtils.getTransactionalEntityManager(emf);

    for (Gifticon gifticon : items) {
      Notification notification = Notification.create();
      em.persist(notification);

      LocalDate today = LocalDate.now();
      LocalDate expireDate = gifticon.getExpireDate();
      int daysLeft = (int) ChronoUnit.DAYS.between(today, expireDate);

      GifticonExpirationAlertNoti gifticonExpirationAlertNoti =
          GifticonExpirationAlertNoti.create(
              notification.getId(),
              gifticon.getId(),
              daysLeft
          );
      em.persist(gifticonExpirationAlertNoti);

      log.info("add gifticon alert noti: {}", gifticon);
    }

    em.flush();
  }
}
