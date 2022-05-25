package com.javid.spring.last.project.phase3.repository;

import com.javid.spring.last.project.phase3.model.base.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author javid
 * Created on 5/18/2022
 */
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
