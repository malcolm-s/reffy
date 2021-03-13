package com.mstone.reffy.register.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.mstone.reffy.govid.GovIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidGovIdValidatorTest {
  @Mock
  private GovIdService govIdService;

  @InjectMocks
  private ValidGovIdValidator validator;

  @Test
  public void isValidReturnsTrueWhenMatchingGovIdExists() {
    when(govIdService.isValidGovId(anyString())).thenReturn(true);

    assertTrue(validator.isValid("1234", null));
  }

  @Test
  public void isValidReturnsFalseWhenNoMatchingGovIdExists() {
    assertFalse(validator.isValid("1234", null));
  }
}
