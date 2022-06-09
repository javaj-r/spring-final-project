package com.javid.sfp.dto.base;

import com.javid.sfp.util.AdvanceInfo;
import com.javid.sfp.util.constraints.FieldsValueMatch;
import com.javid.sfp.util.constraints.UniqueEmail;
import com.javid.sfp.util.constraints.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.javid.sfp.util.validators.Constant.Message.*;
import static com.javid.sfp.util.validators.Constant.Pattern.EMAIL_REGEX;

/**
 * @author javid
 * Created on 5/9/2022
 */
@FieldsValueMatch(field = "password",
        fields = "matchingPassword",
        groups = AdvanceInfo.class)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class UserDto extends BaseDto {

    private Long id;

    @UniqueEmail(groups = AdvanceInfo.class)
    @NotNull(message = NULL_EMAIL, groups = AdvanceInfo.class)
    @Size(min = 5, max = 200, message = EMAIL_SIZE, groups = AdvanceInfo.class)
    @Email(regexp = EMAIL_REGEX, message = INVALID_EMAIL, groups = AdvanceInfo.class)
    private String email;

    @ValidPassword(message = INVALID_PASSWORD, groups = AdvanceInfo.class)
    @NotNull(message = NULL_PASSWORD, groups = AdvanceInfo.class)
    @Size(min = 8, max = 30, message = PASSWORD_SIZE, groups = AdvanceInfo.class)
    private String password;
    private String matchingPassword;

    @NotBlank(message = BLANK_FIRSTNAME, groups = AdvanceInfo.class)
    @Size(min = 1, max = 200, message = FIRSTNAME_SIZE, groups = AdvanceInfo.class)
    private String firstname;

    @NotBlank(message = BLANK_LASTNAME, groups = AdvanceInfo.class)
    @Size(min = 1, max = 200, message = LASTNAME_SIZE, groups = AdvanceInfo.class)
    private String lastname;
}
