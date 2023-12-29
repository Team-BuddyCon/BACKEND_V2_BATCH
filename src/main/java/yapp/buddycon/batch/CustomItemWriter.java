package yapp.buddycon.batch;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.springframework.batch.item.ItemWriter;
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
    EntityManager entityManager = emf.createEntityManager();
    EntityTransaction tx = entityManager.getTransaction();
    tx.begin();

    for (Gifticon gifticon : items) {
      Notification notification = Notification.create();
      entityManager.persist(notification);

      GifticonExpirationAlertNoti gifticonExpirationAlertNoti = GifticonExpirationAlertNoti.create(notification.getId(), gifticon.getId(), 1);
      entityManager.persist(gifticonExpirationAlertNoti);
    }

    tx.commit();
  }
}
