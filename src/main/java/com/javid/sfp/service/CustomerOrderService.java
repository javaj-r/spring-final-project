package com.javid.sfp.service;

import com.javid.sfp.model.CustomerOrder;

import java.util.List;

/**
 * @author javid
 * Created on 5/12/2022
 */
public interface CustomerOrderService {

    List<CustomerOrder> findAll();

    List<CustomerOrder> findAllByCustomerId(Long customerId);

    List<CustomerOrder> findAllByExpertId(Long expertId);

    List<CustomerOrder> findAllAvailableByWorks(Long expertId);

    CustomerOrder findById(Long id);

    CustomerOrder findByIdAndCustomerId(Long id, Long customerId);

    CustomerOrder save(CustomerOrder customerOrder);

    CustomerOrder save(CustomerOrder customerOrder, Long id);

    void deleteByID(Long id);

    CustomerOrder save(Long customerId, Long workId, CustomerOrder order);

    CustomerOrder create(CustomerOrder customerOrder);
}
