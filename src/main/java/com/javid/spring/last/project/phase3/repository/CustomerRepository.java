package com.javid.spring.last.project.phase3.repository;

import com.javid.spring.last.project.phase3.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author javid
 * Created on 5/8/2022
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    Optional<Customer> findByEmailAndPassword(String email, String password);
}
