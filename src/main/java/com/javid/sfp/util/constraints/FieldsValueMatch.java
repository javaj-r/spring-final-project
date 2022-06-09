package com.javid.sfp.util.constraints;

import com.javid.sfp.util.validators.FieldsValueMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author javid
 * Created on 6/4/2022
 */

@Documented
@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface FieldsValueMatch {

    String message() default "Does not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return base field name
     */
    String field();

    /**
     * @return other fields names for matching with field
     */
    String[] fields() default {};

    @Target({ TYPE })
    @Retention(RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
