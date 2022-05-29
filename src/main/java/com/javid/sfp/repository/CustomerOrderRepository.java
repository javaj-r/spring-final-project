package com.javid.sfp.repository;

import com.javid.sfp.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author javid
 * Created on 5/8/2022
 */
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    List<CustomerOrder> findAllByCustomerId(Long customerId);
}
