package com.javid.spring.last.project.phase3.service;

import com.javid.spring.last.project.phase3.model.ExpertOffer;

import java.util.List;

/**
 * @author javid
 * Created on 5/12/2022
 */
public interface ExpertOfferService {

    ExpertOffer findByID(Long id);

    ExpertOffer save(ExpertOffer expertOffer, Long orderId, Long expertId);

    void deleteByID(Long expertId, Long customerOrderId);

    List<ExpertOffer> findAllByOrderId(Long workId);

    ExpertOffer save(Long expertId, Long orderId, ExpertOffer expertOffer);
}
