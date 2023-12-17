package yapp.buddycon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class BuddyconApplication {

  public static void main(String[] args) {
    SpringApplication.run(BuddyconApplication.class, args);
  }

}
