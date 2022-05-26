package com.javid.spring.last.project.phase3.service.impl;

import com.javid.spring.last.project.phase3.exception.ResourceNotFoundException;
import com.javid.spring.last.project.phase3.model.ExpertOffer;
import com.javid.spring.last.project.phase3.model.ExpertOfferId;
import com.javid.spring.last.project.phase3.repository.CustomerOrderRepository;
import com.javid.spring.last.project.phase3.repository.ExpertOfferRepository;
import com.javid.spring.last.project.phase3.repository.ExpertRepository;
import com.javid.spring.last.project.phase3.service.ExpertOfferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author javid
 * Created on 5/12/2022
 */
@Service
@Transactional
public class ExpertOfferServiceImpl implements ExpertOfferService {

    private final ExpertOfferRepository offerRepository;
    private final ExpertRepository expertRepository;
    private final CustomerOrderRepository orderRepository;


    public ExpertOfferServiceImpl(ExpertOfferRepository offerRepository, ExpertRepository expertRepository, CustomerOrderRepository orderRepository) {
        this.offerRepository = offerRepository;
        this.expertRepository = expertRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public ExpertOffer findByID(Long id) {
        return null;
    }

    @Override
    public ExpertOffer save(ExpertOffer expertOffer, Long orderId, Long expertId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
        var expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        expertOffer.setExpert(expert);
        expertOffer.setOrder(order);

        return offerRepository.save(expertOffer);
    }

    @Override
    public void deleteByID(Long expertId, Long customerOrderId) {
        var id = new ExpertOfferId();
        id.setExpertId(expertId);
        id.setCustomerOrderId(customerOrderId);

        offerRepository.deleteById(id);
    }

    @Override
    public List<ExpertOffer> findAllByOrderId(Long workId) {
        return offerRepository.findAllByOrderId(workId);
    }

    @Override
    public ExpertOffer save(Long expertId, Long orderId, ExpertOffer expertOffer) {
        var expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new ResourceNotFoundException("Expert not found!"));

        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found!"));

        var id = new ExpertOfferId();
        id.setExpertId(expertId);
        id.setCustomerOrderId(orderId);

        expertOffer.setId(id);
        expertOffer.setExpert(expert);
        expertOffer.setOrder(order);
        return offerRepository.save(expertOffer);
    }
}
