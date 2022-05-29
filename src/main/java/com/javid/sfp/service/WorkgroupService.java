package com.javid.sfp.service;

import com.javid.sfp.dto.WorkgroupDto;

import java.util.List;

/**
 * @author javid
 * Created on 5/21/2022
 */
public interface WorkgroupService {

    List<WorkgroupDto> findAll();

    WorkgroupDto findById(Long id);
}
