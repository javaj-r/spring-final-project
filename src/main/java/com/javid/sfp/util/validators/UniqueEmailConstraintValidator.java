package com.javid.sfp.util.validators;

import com.javid.sfp.service.UserService;
import com.javid.sfp.util.constraints.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author javid
 * Created on 6/4/2022
 */
public class UniqueEmailConstraintValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService service;

    public UniqueEmailConstraintValidator(UserService service) {
        this.service = service;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !service.existsByEmail(email);
    }
}
