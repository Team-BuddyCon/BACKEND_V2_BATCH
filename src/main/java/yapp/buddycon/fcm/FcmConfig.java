package yapp.buddycon.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FcmConfig {

  @Bean
  public FirebaseMessaging firebaseMessaging() throws IOException {
    ClassPathResource resource = new ClassPathResource("buddycon-48ea8-firebase-adminsdk-kjexl-0e7f3fdbf9.json");
    InputStream refreshToken = resource.getInputStream();

    FirebaseApp firebaseApp = null;
    List<FirebaseApp> firebaseAppList = FirebaseApp.getApps();

    if (firebaseAppList != null && !firebaseAppList.isEmpty()) {
      for (FirebaseApp app : firebaseAppList) {
        if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
          firebaseApp = app;
        }
      }
    } else {
      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(refreshToken))
          .build();
      firebaseApp = FirebaseApp.initializeApp(options);
    }
    return FirebaseMessaging.getInstance(firebaseApp);
  }

// official document : https://console.firebase.google.com/u/3/project/buddycon-48ea8/settings/serviceaccounts/adminsdk?hl=ko
//FileInputStream serviceAccount =
//new FileInputStream("path/to/serviceAccountKey.json");
//
//FirebaseOptions options = new FirebaseOptions.Builder()
//  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//  .build();
//
//FirebaseApp.initializeApp(options);
}
