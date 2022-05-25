package com.javid.spring.last.project.phase3.service;

import com.javid.spring.last.project.phase3.dto.CustomerDto;

/**
 * @author javid
 * Created on 5/9/2022
 */
public interface CustomerService {

    CustomerDto findByID(Long id);

    CustomerDto findByEmailAndPassword(String email, String password);

    CustomerDto save(CustomerDto customerDto);

    CustomerDto save(CustomerDto customerDto, Long id);

    void deleteByID(Long id);

    CustomerDto saveOrUpdate(CustomerDto customer);
}
