package com.javid.sfp.repository;

import com.javid.sfp.model.CustomerOrder;
import com.javid.sfp.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author javid
 *
 * Created on 5/8/2022
 */
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    List<CustomerOrder> findAllByCustomerId(Long customerId);

    List<CustomerOrder> findAllBySelectedExpertId(Long expertId);

    Optional<CustomerOrder> findByIdAndCustomerId(Long id, Long customerId);

    List<CustomerOrder> findAllBySelectedExpertNullAndWorkIn(Collection<Work> works);
}
