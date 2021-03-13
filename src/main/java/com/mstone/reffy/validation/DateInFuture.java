package com.mstone.reffy.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DateInFutureValidator.class })
@Documented
public @interface DateInFuture {
  String message() default "Date must be in future";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
