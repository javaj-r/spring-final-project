package com.javid.spring.last.project.phase3.service;

import com.javid.spring.last.project.phase3.dto.ExpertDto;

/**
 * @author javid
 * Created on 5/12/2022
 */
public interface ExpertService {

    ExpertDto findByID(Long id);

    ExpertDto findByEmailAndPassword(String email, String password);

    ExpertDto saveOrUpdate(ExpertDto expertDto);

    void deleteByID(Long id);

    boolean existsByEmail(String email);
}
