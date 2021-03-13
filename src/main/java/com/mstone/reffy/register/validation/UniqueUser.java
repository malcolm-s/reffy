package com.mstone.reffy.register.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueUserValidator.class })
@Documented
public @interface UniqueUser {
  String message() default "A user with these credentials already exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
