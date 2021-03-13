package com.mstone.reffy.register;

import com.mstone.reffy.email.EmailService;
import com.mstone.reffy.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistrationListener {
  private final EmailService emails;
  private final UserRepository users;

  @EventListener
  @Async
  public void onRegister(RegistrationEvent event) {
    users
      .findById(event.getUserId())
      .ifPresent(
        user -> {
          emails.sendEmail(
            user.getEmail(),
            "Welcome to Reffy!",
            "Thanks for joining our digital democracy platform"
          );
          log.info("sent registration email");
        }
      );
  }
}
