package com.javid.sfp.service;

import com.javid.sfp.dto.ExpertDto;
import com.javid.sfp.model.Expert;
import com.javid.sfp.model.Work;

import java.util.List;

/**
 * @author javid
 * Created on 5/12/2022
 */
public interface ExpertService {

    ExpertDto findByID(Long id);

    ExpertDto findByEmailAndPassword(String email, String password);

    Expert create(Expert expert);

    ExpertDto saveOrUpdate(ExpertDto expertDto);

    void deleteByID(Long id);

    boolean existsByEmail(String email);

    List<Expert> findAllByCondition(Expert expert, String enrolledWorkName);

    List<Expert> findAll();

    Expert findById(Long id);

    void update(Expert expert);

    List<Work> findExpertEnrolledWorks(Long expertId);
}
