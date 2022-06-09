package com.javid.sfp.service.impl;

import com.javid.sfp.exception.BadRequestException;
import com.javid.sfp.exception.ResourceNotFoundException;
import com.javid.sfp.model.CustomerOrder;
import com.javid.sfp.model.enums.OrderStatus;
import com.javid.sfp.repository.CustomerOrderRepository;
import com.javid.sfp.service.CustomerOrderService;
import com.javid.sfp.service.CustomerService;
import com.javid.sfp.service.ExpertService;
import com.javid.sfp.service.WorkService;
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

    private final CustomerOrderRepository orderRepository;
    private final ExpertService expertService;
    private final CustomerService customerService;
    private final WorkService workService;

    public CustomerOrderServiceImpl(CustomerOrderRepository orderRepository, ExpertService expertService
            , CustomerService customerService, WorkService workService) {
        this.orderRepository = orderRepository;
        this.expertService = expertService;
        this.customerService = customerService;
        this.workService = workService;
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
    public List<CustomerOrder> findAllByExpertId(Long expertId) {
        return orderRepository.findAllBySelectedExpertId(expertId);
    }

    @Override
    public List<CustomerOrder> findAllAvailableByWorks(Long expertId) {
        var works = expertService.findExpertEnrolledWorks(expertId);

        return orderRepository.findAllBySelectedExpertNullAndWorkIn(works);
    }

    @Override
    public CustomerOrder findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not exists!"));
    }

    @Override
    public CustomerOrder findByIdAndCustomerId(Long id, Long customerId) {
        return orderRepository.findByIdAndCustomerId(id, customerId)
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
        var customer = customerService.findByID(customerId);
        var work = workService.findById(workId);

        order.setCustomer(customer);
        order.setWork(work);
        return orderRepository.save(order);
    }

    @Override
    public CustomerOrder create(CustomerOrder customerOrder) {
        var customer = customerService.findByID(customerOrder.getCustomer().getId());
        var work = workService.findById(customerOrder.getWork().getId());
        var proposedPrice = customerOrder.getProposedPrice() == null ?
                work.getBasePrice() : customerOrder.getProposedPrice();

        if (work.getBasePrice().compareTo(proposedPrice) < 0) {
            throw new BadRequestException("Invalid Proposed Price!");
        }

        var order = new CustomerOrder();
        order.setCustomer(customer);
        order.setWork(work);
        order.setProposedPrice(proposedPrice);
        order.setDescription(customerOrder.getDescription());
        order.setOrderStatus(OrderStatus.AWAITING_SUGGESTION);
        order.setWorkDate(customerOrder.getWorkDate());
        order.setWorkTime(customerOrder.getWorkTime());
        order.setAddress(customerOrder.getAddress());

        return orderRepository.save(order);
    }
}
