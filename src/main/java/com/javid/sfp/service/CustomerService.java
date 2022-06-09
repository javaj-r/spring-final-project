package com.javid.sfp.service;

import com.javid.sfp.dto.CustomerDto;
import com.javid.sfp.model.Customer;

import java.util.List;

/**
 * @author javid
 * Created on 5/9/2022
 */
public interface CustomerService {

    Customer findByID(Long id);

    CustomerDto findByEmailAndPassword(String email, String password);

    Customer create(Customer customer);

    void deleteByID(Long id);

    void update(Customer customer);

    List<CustomerDto> findAllByNameAndEmail(CustomerDto customerDto);

    List<Customer> findAll();

    Customer findByEmail(String email);
}
