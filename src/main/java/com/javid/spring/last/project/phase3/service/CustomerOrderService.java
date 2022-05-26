package com.javid.spring.last.project.phase3.service;

import com.javid.spring.last.project.phase3.model.CustomerOrder;

import java.util.List;

/**
 * @author javid
 * Created on 5/12/2022
 */
public interface CustomerOrderService {

    List<CustomerOrder> findAll();

    List<CustomerOrder> findAllByCustomerId(Long customerId);

    CustomerOrder findById(Long id);

    CustomerOrder save(CustomerOrder customerOrder);

    CustomerOrder save(CustomerOrder customerOrder, Long id);

    void deleteByID(Long id);

    CustomerOrder save(Long customerId, Long workId, CustomerOrder order);
}
