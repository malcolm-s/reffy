package com.mstone.reffy.referendum.validation;

import java.time.Clock;
import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DateInFutureValidator implements ConstraintValidator<DateInFuture, LocalDateTime> {
  private final Clock clock;
  
  @Override
  public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
    return value.isAfter(LocalDateTime.now(clock));
  }
}
