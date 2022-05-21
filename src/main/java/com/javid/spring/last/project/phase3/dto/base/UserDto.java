package com.javid.spring.last.project.phase3.dto.base;

import com.javid.spring.last.project.phase3.util.ValidPassword;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.javid.spring.last.project.phase3.util.Validator.Message.*;
import static com.javid.spring.last.project.phase3.util.Validator.Pattern.EMAIL_REGEX;

/**
 * @author javid
 * Created on 5/9/2022
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Accessors(chain = true)
public abstract class UserDto extends BaseDto {

    private Long id;

    @NotNull(message = NULL_EMAIL)
    @Size(min = 5, max = 200, message = EMAIL_SIZE)
    @Email(regexp = EMAIL_REGEX, message = INVALID_EMAIL)
    private String email;

    @ValidPassword(message = INVALID_PASSWORD)
    @NotNull(message = NULL_PASSWORD)
    @Size(min = 8, max = 30, message = PASSWORD_SIZE)
    private String password;

    @NotBlank(message = BLANK_FIRSTNAME)
    @Size(min = 1, max = 200, message = FIRSTNAME_SIZE)
    private String firstname;

    @NotBlank(message = BLANK_LASTNAME)
    @Size(min = 1, max = 200, message = LASTNAME_SIZE)
    private String lastname;
}
