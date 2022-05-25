package com.javid.spring.last.project.phase3.service;

import com.javid.spring.last.project.phase3.dto.WorkgroupDto;

import java.util.List;

/**
 * @author javid
 * Created on 5/21/2022
 */
public interface WorkgroupService {

    List<WorkgroupDto> findAll();

    WorkgroupDto findById(Long id);
}
