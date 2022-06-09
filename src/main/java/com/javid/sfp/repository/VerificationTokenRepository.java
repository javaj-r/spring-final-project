package com.javid.sfp.repository;

import com.javid.sfp.model.VerificationToken;
import com.javid.sfp.model.base.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * @author javid
 * Created on 6/4/2022
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByUser(User user);

    Optional<VerificationToken> findByToken(String token);

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT (t.expiryTime < CURRENT_TIMESTAMP) FROM VerificationToken t WHERE t.id = :id ")
    boolean isExpiredById(@Param("id") Long id);

    @Transactional(readOnly = true)
    @Query(value = "SELECT CURRENT_TIMESTAMP", nativeQuery = true)
    Timestamp getCurrentTime();
}
