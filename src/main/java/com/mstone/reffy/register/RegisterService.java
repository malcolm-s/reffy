package com.mstone.reffy.register;

import com.mstone.reffy.user.User;
import com.mstone.reffy.user.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User register(RegisterForm vm) {
    var user = new User();
    user.setEmail(vm.getEmail());
    user.setPassword(passwordEncoder.encode(vm.getPassword()));
    user.setGovId(vm.getGovId());
    return userRepository.save(user);
  }
}