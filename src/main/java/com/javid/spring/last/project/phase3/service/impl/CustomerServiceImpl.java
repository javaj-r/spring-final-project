package com.javid.spring.last.project.phase3.service.impl;

import com.javid.spring.last.project.phase3.dto.CustomerDto;
import com.javid.spring.last.project.phase3.exception.ResourceNotFoundException;
import com.javid.spring.last.project.phase3.mapper.CustomerMapper;
import com.javid.spring.last.project.phase3.repository.CustomerRepository;
import com.javid.spring.last.project.phase3.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author javid
 * Created on 5/10/2022
 */
@Slf4j
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto findByID(Long id) {
        return null;
    }

    @Override
    public CustomerDto findByEmailAndPassword(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password)
                .map(customerMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Wrong Email or Password!"));
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        return save(customerDto, null);
    }

    @Override
    public CustomerDto save(CustomerDto customerDto, Long id) {
        var admin = customerMapper.mapToEntity(customerDto);
        admin.setId(id);

        return customerMapper.mapToDto(customerRepository.save(admin));
    }

    @Override
    public void deleteByID(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDto saveOrUpdate(CustomerDto customerDto) {
        log.debug("CustomerServiceImpl: saveOrUpdate");

        var customer = customerMapper.mapToEntity(customerDto);
        var savedCustomer = customer != null ? customerRepository.save(customer) : null;
        log.debug(savedCustomer != null ? "Saved Customer: " + savedCustomer.getId() : "Saved Customer is null!");

        return customerMapper.mapToDto(savedCustomer);
    }
}
