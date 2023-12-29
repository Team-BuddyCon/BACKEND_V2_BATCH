package yapp.buddycon.batch;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.springframework.batch.item.ItemWriter;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import yapp.buddycon.domain.Gifticon;
import yapp.buddycon.domain.GifticonExpirationAlertNoti;
import yapp.buddycon.domain.Notification;

public class CustomItemWriter implements ItemWriter<Gifticon> {

  private final EntityManagerFactory emf;

  public CustomItemWriter(EntityManagerFactory emf) {
    this.emf = emf;
  }

  @Override
  public void write(List<? extends Gifticon> items) throws Exception {
    EntityManager em = EntityManagerFactoryUtils.getTransactionalEntityManager(emf);

    for (Gifticon gifticon : items) {
      Notification notification = Notification.create();
      em.persist(notification);

      GifticonExpirationAlertNoti gifticonExpirationAlertNoti = GifticonExpirationAlertNoti.create(notification.getId(), gifticon.getId(), 1);
      em.persist(gifticonExpirationAlertNoti);
    }

    em.flush();
  }
}
