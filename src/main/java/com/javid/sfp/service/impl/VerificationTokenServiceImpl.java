package com.javid.sfp.service.impl;

import com.javid.sfp.exception.ResourceNotFoundException;
import com.javid.sfp.model.VerificationToken;
import com.javid.sfp.repository.VerificationTokenRepository;
import com.javid.sfp.service.VerificationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author javid
 * Created on 6/4/2022
 */
@Slf4j
@Service
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;

    public VerificationTokenServiceImpl(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public VerificationToken saveOrUpdate(VerificationToken token) {
        return tokenRepository.save(token);
    }

    @Override
    public VerificationToken saveOrUpdate(VerificationToken token, int expiryTimeInMinutes) {
        var expiryTime = calculateExpiryTime(tokenRepository.getCurrentTime(), expiryTimeInMinutes);
        token.setExpiryTime(expiryTime);
        return saveOrUpdate(token);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token not found!"));
    }

    @Override
    public boolean isExpiredById(Long id) {
        return tokenRepository.isExpiredById(id);
    }

    @Override
    public Timestamp calculateExpiryTime(Timestamp from, int expiryMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        cal.add(Calendar.MINUTE, expiryMinutes);

        return new Timestamp(cal.getTime().getTime());
    }

    @Override
    public void deleteById(Long id) {
        tokenRepository.deleteById(id);
    }
}
