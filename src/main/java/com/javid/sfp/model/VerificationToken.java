package com.javid.sfp.model;

import com.javid.sfp.model.base.BaseEntity;
import com.javid.sfp.model.base.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

/**
 * @author javid
 * Created on 6/3/2022
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class VerificationToken extends BaseEntity<Long> {

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private String token;

    private Timestamp expiryTime;

    public VerificationToken(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
