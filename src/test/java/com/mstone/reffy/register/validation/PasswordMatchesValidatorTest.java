package com.mstone.reffy.register.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordMatchesValidatorTest {
  private PasswordMatchesValidator validator;

  @BeforeEach
  public void setup() {
    validator = new PasswordMatchesValidator();
  }

  @Test
  public void isValidReturnsTrueWhenPasswordsMatch() {
    assertTrue(validator.isValid(new MatchingPasswordForm() {
      @Override
      public String getPassword() {
        return "asdf";
      }

      @Override
      public String getRepeatPassword() {
        return "asdf";
      }
    }, null));
  }

  @Test
  public void isValidReturnsFalseWhenPasswordsMatch() {
    assertFalse(validator.isValid(new MatchingPasswordForm() {
      @Override
      public String getPassword() {
        return "asdf";
      }

      @Override
      public String getRepeatPassword() {
        return "asd";
      }
    }, null));
  }
}