package com.mstone.reffy.register.validation;

import com.mstone.reffy.govid.GovIdService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidGovIdValidator implements ConstraintValidator<ValidGovId, String> {
  private final GovIdService govIdService;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return govIdService.isValidGovId(value);
  }
}
