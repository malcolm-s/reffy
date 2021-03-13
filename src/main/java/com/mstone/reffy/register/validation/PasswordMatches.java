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
@Constraint(validatedBy = { PasswordMatchesValidator.class })
@Documented
public @interface PasswordMatches {
  String message() default "Password and repeat password must match";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
