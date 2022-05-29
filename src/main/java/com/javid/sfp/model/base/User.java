package com.javid.sfp.model.base;

import com.javid.sfp.util.ValidPassword;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

import static com.javid.sfp.util.Validator.Message.*;
import static com.javid.sfp.util.Validator.Pattern.EMAIL_REGEX;

/**
 * @author javid
 * Created on 4/30/2022
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity(name = "APP_USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.INTEGER)
public abstract class User extends BaseEntity<Long> {

    @Setter(AccessLevel.NONE)
    @Column(name = "USER_TYPE", insertable = false, updatable = false)
    private Integer discriminator;

    @NotNull(message = NULL_EMAIL)
    @Size(min = 5, max = 200, message = EMAIL_SIZE)
    @Email(regexp = EMAIL_REGEX, message = INVALID_EMAIL)
    @Column(name = "EMAIL", length = 200, nullable = false, unique = true)
    private String email;

    @ValidPassword(message = INVALID_PASSWORD)
    @NotNull(message = NULL_PASSWORD)
    @Size(min = 8, max = 30, message = PASSWORD_SIZE)
    @Column(name = "PASSWORD", length = 30, nullable = false)
    private String password;

    @NotBlank(message = BLANK_FIRSTNAME)
    @Size(min = 1, max = 200, message = FIRSTNAME_SIZE)
    @Column(name = "FIRSTNAME", length = 200, nullable = false)
    private String firstname;

    @NotBlank(message = BLANK_LASTNAME)
    @Size(min = 1, max = 200, message = LASTNAME_SIZE)
    @Column(name = "LASTNAME", length = 200, nullable = false)
    private String lastname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User that = (User) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

}
