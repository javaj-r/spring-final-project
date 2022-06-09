package com.javid.sfp.model.base;

import com.javid.sfp.util.constraints.ValidPassword;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.javid.sfp.util.validators.Constant.Message.*;
import static com.javid.sfp.util.validators.Constant.Pattern.EMAIL_REGEX;

/**
 * @author javid
 * Created on 4/30/2022
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "APP_USER",
        indexes = @Index(name = "username", columnList = "email", unique = true))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.INTEGER)
public class User extends BaseEntity<Long> implements UserDetails {

    @Setter(AccessLevel.NONE)
    @Column(name = "USER_TYPE", insertable = false, updatable = false)
    private String discriminator;

    @NotNull(message = NULL_EMAIL)
    @Size(min = 5, max = 200, message = EMAIL_SIZE)
    @Email(regexp = EMAIL_REGEX, message = INVALID_EMAIL)
    @Column(name = "EMAIL", length = 200, nullable = false, unique = true)
    private String email;

    @ValidPassword(min = 8, max = 100 ,message = INVALID_PASSWORD)
    @NotNull(message = NULL_PASSWORD)
    @Size(min = 8, max = 100, message = PASSWORD_SIZE)
    @Column(name = "PASSWORD", length = 100, nullable = false)
    private String password;

    @NotBlank(message = BLANK_FIRSTNAME)
    @Size(min = 1, max = 200, message = FIRSTNAME_SIZE)
    @Column(name = "FIRSTNAME", length = 200, nullable = false)
    private String firstname;

    @NotBlank(message = BLANK_LASTNAME)
    @Size(min = 1, max = 200, message = LASTNAME_SIZE)
    @Column(name = "LASTNAME", length = 200, nullable = false)
    private String lastname;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "user_role")
    private List<String> roles = new ArrayList<>();

    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean enabled;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
