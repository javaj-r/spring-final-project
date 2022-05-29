package com.javid.sfp.service;

import com.javid.sfp.dto.ExpertDto;

import java.util.List;

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

    List<ExpertDto> findAllByCondition(ExpertDto expertDto);
}
