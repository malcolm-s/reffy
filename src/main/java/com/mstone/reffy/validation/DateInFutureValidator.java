package com.mstone.reffy.validation;

import java.time.Clock;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DateInFutureValidator implements ConstraintValidator<DateInFuture, LocalDate> {
  private final Clock clock;

  @Override
  public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
    if (value != null) {
      return value.isAfter(LocalDate.now(clock));
    }
    return false;
  }
}
