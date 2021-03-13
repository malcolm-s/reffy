package com.mstone.reffy.register;

import com.mstone.reffy.user.User;
import com.mstone.reffy.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ApplicationEventPublisher publisher;

  public User register(RegisterForm vm) {
    var user = new User();
    user.setEmail(vm.getEmail());
    user.setPassword(passwordEncoder.encode(vm.getPassword()));
    user.setGovId(vm.getGovId());
    userRepository.save(user);

    publisher.publishEvent(new RegistrationEvent(user.getId()));
    return user;
  }
}
