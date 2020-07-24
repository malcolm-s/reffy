package com.mstone.reffy.register.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidGovIdValidator.class })
@Documented
public @interface ValidGovId {
  String message() default "Invalid Government ID";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}