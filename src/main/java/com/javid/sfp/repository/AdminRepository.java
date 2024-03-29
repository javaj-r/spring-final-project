package com.javid.sfp.repository;

import com.javid.sfp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author javid
 * Created on 5/8/2022
 */
public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin> {

    Optional<Admin> findByEmail(String email);
}
