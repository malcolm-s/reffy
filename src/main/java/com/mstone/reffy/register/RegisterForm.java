package com.mstone.reffy.register;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.mstone.reffy.register.validation.MatchingPasswordForm;
import com.mstone.reffy.register.validation.PasswordMatches;
import com.mstone.reffy.register.validation.UniqueUser;

import lombok.Data;

@Data
@PasswordMatches
@UniqueUser
public class RegisterForm implements MatchingPasswordForm {
  @NotBlank
  @Email
  private String email;
  @NotBlank
  @Size(min = 8, max = 30)
  private String password;
  @NotBlank
  private String repeatPassword;
}