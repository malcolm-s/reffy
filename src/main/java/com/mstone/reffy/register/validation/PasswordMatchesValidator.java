package com.mstone.reffy.register.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class PasswordMatchesValidator
  implements ConstraintValidator<PasswordMatches, MatchingPasswordForm> {

  // See https://www.baeldung.com/spring-mvc-custom-validator for a more generic field matching validator
  @Override
  public boolean isValid(
    final MatchingPasswordForm value,
    final ConstraintValidatorContext context
  ) {
    return value.getPassword().equals(value.getRepeatPassword());
  }
}
