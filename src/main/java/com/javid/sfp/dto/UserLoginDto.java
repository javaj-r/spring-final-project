package com.javid.sfp.dto;

import com.javid.sfp.util.constraints.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.javid.sfp.util.validators.Constant.Pattern.EMAIL_REGEX;

/**
 * @author javid
 * Created on 5/30/2022
 */
@Getter
@Setter
public class UserLoginDto {

    @NotNull
    @Size(min = 5, max = 200)
    @Email(regexp = EMAIL_REGEX)
    private String email;

    @ValidPassword
    private String password;
}
