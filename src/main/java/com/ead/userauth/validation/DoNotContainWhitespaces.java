package com.ead.userauth.validation;

import com.ead.userauth.validation.constraint.DoNotContainWhitespacesConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = DoNotContainWhitespacesConstraint.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoNotContainWhitespaces {

    String message() default "Must not contain any whitespaces.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
