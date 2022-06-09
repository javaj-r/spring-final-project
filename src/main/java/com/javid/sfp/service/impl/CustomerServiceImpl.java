package com.javid.sfp.service.impl;

import com.javid.sfp.dto.CustomerDto;
import com.javid.sfp.exception.BadRequestException;
import com.javid.sfp.exception.ResourceNotFoundException;
import com.javid.sfp.mapper.CustomerMapper;
import com.javid.sfp.model.Customer;
import com.javid.sfp.repository.CustomerRepository;
import com.javid.sfp.repository.specification.SearchCriteria;
import com.javid.sfp.repository.specification.UserSpecification;
import com.javid.sfp.security.Role;
import com.javid.sfp.service.CustomerService;
import com.javid.sfp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    private final UserService userService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, UserService userService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.userService = userService;
    }

    @Override
    public Customer findByID(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found!"));
    }

    @Override
    public CustomerDto findByEmailAndPassword(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password)
                .map(customerMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Wrong Email or Password!"));
    }

    @Override
    public Customer create(Customer customer) {
        if (userService.existsByEmail(customer.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        customer.getRoles().add(Role.CUSTOMER);
        userService.encodePassword(customer);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteByID(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void update(Customer customer) {
        var optional = customerRepository.findById(customer.getId());
        if (optional.isPresent()) {
            var fetched = optional.get();
            var email = customer.getEmail();
            if (!fetched.getEmail().equals(email) && userService.existsByEmail(email)) {
                throw new BadRequestException("Email already exists");
            }
            customer.setPassword(fetched.getPassword());
            customerRepository.save(customer);
        }
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
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found!"));
    }
}
