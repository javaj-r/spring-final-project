package com.javid.sfp.service;

import com.javid.sfp.model.VerificationToken;

import java.sql.Timestamp;

/**
 * @author javid
 * Created on 6/4/2022
 */
public interface VerificationTokenService {

    VerificationToken saveOrUpdate(VerificationToken token);

    VerificationToken saveOrUpdate(VerificationToken token, int expiryTimeInMinutes);

    VerificationToken findByToken(String token);

    boolean isExpiredById(Long id);

    Timestamp calculateExpiryTime(Timestamp from, int expiryMinutes);

    void deleteById(Long id);
}
