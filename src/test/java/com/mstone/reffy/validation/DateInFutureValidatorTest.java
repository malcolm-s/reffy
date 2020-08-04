package com.mstone.reffy.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

public class DateInFutureValidatorTest {
  @Test
  public void isValidReturnsTrueWhenPasswordsMatch() {
    var fifthJanuary = fixedClock("2020-01-05T00:00:00.00Z");
    var sixthJanuary = fixedClock("2020-01-06T00:00:00.00Z");
    var validator = new DateInFutureValidator(fifthJanuary);

    assertTrue(validator.isValid(LocalDate.now(sixthJanuary), null));
  }

  private Clock fixedClock(String date) {
    return Clock.fixed(Instant.parse(date), ZoneId.of("UTC"));
  }

  @Test
  public void isValidReturnsFalseWhenPasswordsMatch() {
    var fourthJanuary = fixedClock("2020-01-04T00:00:00.00Z");
    var fifthJanuary = fixedClock("2020-01-05T00:00:00.00Z");
    var validator = new DateInFutureValidator(fifthJanuary);

    assertFalse(validator.isValid(LocalDate.now(fourthJanuary), null));
  }

  @Test
  public void isValidHandlesNull() {
    var fifthJanuary = fixedClock("2020-01-05T00:00:00.00Z");
    var validator = new DateInFutureValidator(fifthJanuary);

    assertFalse(validator.isValid(null, null));
  }
}