package yapp.buddycon.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FcmController {

  private final FirebaseMessaging firebaseMessaging;

  @GetMapping("/fcm")
  public String fcm(FcmRequestDTO dto) {
    Message messsage = Message.builder()
        .putData("title", dto.title())
        .putData("body", dto.body())
        .setToken(dto.userFirebaseToken())
        .build();

    try {
      firebaseMessaging.send(messsage);
      return "알림 발송 성공";
    } catch (FirebaseMessagingException e) {
      return e.getMessage();
    }
  }

  record FcmRequestDTO(String userFirebaseToken, String title, String body) {
  }

}
