package com.javid.sfp.repository;

import com.javid.sfp.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author javid
 * Created on 5/8/2022
 */
public interface ExpertRepository extends JpaRepository<Expert, Long>, JpaSpecificationExecutor<Expert> {

    Optional<Expert> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
