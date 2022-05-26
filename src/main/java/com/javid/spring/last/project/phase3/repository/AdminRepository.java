package com.javid.spring.last.project.phase3.repository;

import com.javid.spring.last.project.phase3.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author javid
 * Created on 5/8/2022
 */
public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin> {

    Optional<Admin> findByEmailAndPassword(String email, String password);
}
