package com.card.procession.credit.handler;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CreditCardValidator.class)
@Documented
public @interface CreditCard {
  String message() default "Invalid card number based on Luhn 10 algo";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
