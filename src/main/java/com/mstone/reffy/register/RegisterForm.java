package com.mstone.reffy.register;

import javax.validation.constraints.NotBlank;

import com.mstone.reffy.register.validation.MatchingPasswordForm;
import com.mstone.reffy.register.validation.PasswordMatches;

import lombok.Data;

@Data
@PasswordMatches
public class RegisterForm implements MatchingPasswordForm {
  @NotBlank
  private String email;
  @NotBlank
  private String password;
  @NotBlank
  private String repeatPassword;
}