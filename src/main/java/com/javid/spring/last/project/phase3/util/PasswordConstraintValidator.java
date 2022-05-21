package com.javid.spring.last.project.phase3.util;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author javid
 * Created on 5/20/2022
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    /**
     * <p>
     * <strong>Roles:</strong>
     * <ol>
     *     <li>at least 8 characters</li>
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
        PasswordValidator validator = new PasswordValidator(List.of(
                new LengthRule(8, 30),
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
