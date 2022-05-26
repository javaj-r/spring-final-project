package com.javid.spring.last.project.phase3.service.impl;

import com.javid.spring.last.project.phase3.exception.ResourceNotFoundException;
import com.javid.spring.last.project.phase3.model.CustomerOrder;
import com.javid.spring.last.project.phase3.repository.CustomerOrderRepository;
import com.javid.spring.last.project.phase3.repository.CustomerRepository;
import com.javid.spring.last.project.phase3.repository.WorkRepository;
import com.javid.spring.last.project.phase3.service.CustomerOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author javid
 * Created on 5/12/2022
 */
@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerRepository customerRepository;
    private final WorkRepository workRepository;
    private final CustomerOrderRepository orderRepository;

    public CustomerOrderServiceImpl(CustomerRepository customerRepository, WorkRepository workRepository, CustomerOrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.workRepository = workRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<CustomerOrder> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<CustomerOrder> findAllByCustomerId(Long customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    @Override
    public CustomerOrder findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not exists!"));
    }

    @Override
    public CustomerOrder save(CustomerOrder customerOrder) {
        return save(customerOrder, null);
    }

    @Override
    public CustomerOrder save(CustomerOrder customerOrder, Long id) {
        customerOrder.setId(id);

        return orderRepository.save(customerOrder);
    }

    @Override
    public void deleteByID(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public CustomerOrder save(Long customerId, Long workId, CustomerOrder order) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found!"));
        var work = workRepository.findById(workId)
                .orElseThrow(() -> new ResourceNotFoundException("Work not found!"));

        order.setCustomer(customer);
        order.setWork(work);
        return orderRepository.save(order);
    }
}
