package com.javid.sfp.util.validators;

import com.javid.sfp.util.constraints.ValidPassword;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author javid
 * Created on 5/20/2022
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private int max;
    private int min;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        max = constraintAnnotation.max();
        min = constraintAnnotation.min();
    }

    /**
     * <p>
     * <strong>Rules:</strong>
     * <ol>
     *     <li>minimum length equal to min</li>
     *     <li>maximum length equal to max</li>
     *     <li>at least one upper-case character</li>
     *     <li>at least one lower-case character</li>
     *     <li>at least one digit character</li>
     *     <li>at least one symbol (special character)</li>
     *     <li>no whitespace</li>
     * </ol>
     * </p>
     *
     * @param password string password value
     * @param context  validator context
     * @return boolean false if not valid password
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null)
            return false;

        PasswordValidator validator = new PasswordValidator(List.of(
                new LengthRule(min, max),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = String.join(",", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

}
