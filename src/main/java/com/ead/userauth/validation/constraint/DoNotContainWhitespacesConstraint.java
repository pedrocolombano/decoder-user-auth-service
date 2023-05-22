package com.ead.userauth.validation.constraint;

import com.ead.userauth.validation.DoNotContainWhitespaces;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DoNotContainWhitespacesConstraint implements ConstraintValidator<DoNotContainWhitespaces, String> {

    @Override
    public boolean isValid(String text, ConstraintValidatorContext constraintValidatorContext) {
        return !StringUtils.containsWhitespace(text);
    }

}
