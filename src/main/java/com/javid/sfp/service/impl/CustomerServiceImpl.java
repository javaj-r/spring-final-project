package com.javid.sfp.service.impl;

import com.javid.sfp.dto.CustomerDto;
import com.javid.sfp.exception.ResourceNotFoundException;
import com.javid.sfp.mapper.CustomerMapper;
import com.javid.sfp.model.Customer;
import com.javid.sfp.repository.CustomerRepository;
import com.javid.sfp.repository.specification.SearchCriteria;
import com.javid.sfp.repository.specification.UserSpecification;
import com.javid.sfp.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CustomerDto> findAllByNameAndEmail(CustomerDto customerDto) {
        var firstname = customerDto.getFirstname() == null ? "" : customerDto.getFirstname();
        var lastname = customerDto.getLastname() == null ? "" : customerDto.getLastname();
        var email = customerDto.getEmail() == null ? "" : customerDto.getEmail();

        var criteria = SearchCriteria.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .build();
        var spec = new UserSpecification<Customer>(criteria);

        return customerMapper.mapToDto(customerRepository.findAll(spec));
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
