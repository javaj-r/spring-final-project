package com.javid.sfp.util.validators;

import com.javid.sfp.util.constraints.FieldsValueMatch;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @author javid
 * Created on 6/4/2022
 */
public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    /**
     * we can use [ new BeanWrapperImpl(value).getPropertyValue(field) ]
     */
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    private String[] fields;
    private String field;
    private String errorMessage;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        field = constraintAnnotation.field();
        fields = constraintAnnotation.fields();
        errorMessage = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (fields.length == 0)
            return true;

        var result = true;
        var base = PARSER.parseExpression(field).getValue(value);
        context.disableDefaultConstraintViolation();

        for (var f : fields) {
            var mach = PARSER.parseExpression(f).getValue(value);
            if (!Objects.equals(base, mach)) {
                context
                        .buildConstraintViolationWithTemplate(errorMessage)
                        .addPropertyNode(f).addConstraintViolation();
                result = false;
            }
        }

        return result;
    }
}
