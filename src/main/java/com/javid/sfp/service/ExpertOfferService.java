package com.javid.sfp.service;

import com.javid.sfp.model.ExpertOffer;
import com.javid.sfp.model.ExpertOfferId;

import java.util.List;

/**
 * @author javid
 * Created on 5/12/2022
 */
public interface ExpertOfferService {

    ExpertOffer findByID(Long id);

    ExpertOffer save(ExpertOffer expertOffer, Long orderId, Long expertId);

    void deleteByID(Long expertId, Long customerOrderId);

    List<ExpertOffer> findAllByOrderId(Long orderId);

    ExpertOffer save(Long expertId, Long orderId, ExpertOffer expertOffer);

    List<ExpertOffer> findAll();

    ExpertOffer findById(ExpertOfferId id);
}
