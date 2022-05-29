package com.javid.sfp.repository;

import com.javid.sfp.model.base.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author javid
 * Created on 5/18/2022
 */
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
