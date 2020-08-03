package com.mstone.reffy.register.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mstone.reffy.register.RegisterForm;
import com.mstone.reffy.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUserValidator implements ConstraintValidator<UniqueUser, RegisterForm> {
  private final UserRepository userRepository;

  @Override
  public boolean isValid(RegisterForm value, ConstraintValidatorContext context) {
    return userRepository.findByEmail(value.getEmail()).isEmpty();
  }
}