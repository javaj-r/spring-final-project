package com.javid.sfp.util.constraints;

import com.javid.sfp.util.validators.UniqueEmailConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author javid
 * Created on 6/4/2022
 */
@Documented
@Constraint(validatedBy = UniqueEmailConstraintValidator.class)
@Target({ FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RUNTIME)
public @interface UniqueEmail {

    String message() default "Duplicate email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
