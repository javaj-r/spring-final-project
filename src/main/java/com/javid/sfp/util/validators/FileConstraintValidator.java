package com.javid.sfp.util.validators;

import com.javid.sfp.util.constraints.ValidFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;

/**
 * @author javid
 * Created on 5/24/2022
 */
@Slf4j
public class FileConstraintValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private int max;
    private int min;
    private String[] validTypes;

    @Override
    public void initialize(ValidFile constraintAnnotation) {
        max = constraintAnnotation.max();
        min = constraintAnnotation.min();
        validTypes = constraintAnnotation.fileType();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        if (file != null && !file.isEmpty()) {
            byte[] bytes;

            try {
                bytes = file.getBytes();
            } catch (IOException e) {
                log.error("MultipartFile converting to byte array error ", e);
                return false;
            }

            if (bytes.length < min || bytes.length > max) {
                return false;
            }

            var type = new Tika().detect(bytes);

            for (var t : validTypes) {
                if (t.equals(type)) {
                    return true;
                }
            }
        }

        return false;
    }
}
