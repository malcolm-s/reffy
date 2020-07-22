package com.mstone.reffy.register;

import com.mstone.reffy.user.User;
import com.mstone.reffy.user.UserRepository;

public class RegisterService {
  private final UserRepository userRepository;

  public RegisterService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User register(RegisterForm vm) {
    var user = new User();
    user.setEmail(vm.getEmail());
    user.setPassword(vm.getPassword());
    return userRepository.save(user);
  }
}