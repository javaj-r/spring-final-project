package com.javid.spring.last.project.phase3.repository;

import com.javid.spring.last.project.phase3.model.ExpertOffer;
import com.javid.spring.last.project.phase3.model.ExpertOfferId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author javid
 * Created on 5/8/2022
 */
public interface ExpertOfferRepository extends JpaRepository<ExpertOffer, ExpertOfferId> {

    List<ExpertOffer> findAllByOrderId(Long workId);
}
