package com.mstone.reffy.register;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RegisterForm {
  @NotBlank
  private String email;
  @NotBlank
  private String password;
  @NotBlank
  private String repeatPassword;
}