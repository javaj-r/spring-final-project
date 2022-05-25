package com.javid.spring.last.project.phase3.service;

import com.javid.spring.last.project.phase3.dto.WorkDto;

import java.util.List;

/**
 * @author javid
 * Created on 5/23/2022
 */
public interface WorkService {

    List<WorkDto> findAllByWorkgroupId(Long id);
}
