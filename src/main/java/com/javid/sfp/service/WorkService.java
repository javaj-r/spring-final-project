package com.javid.sfp.service;

import com.javid.sfp.dto.WorkDto;

import java.util.List;

/**
 * @author javid
 * Created on 5/23/2022
 */
public interface WorkService {

    List<WorkDto> findAllByWorkgroupId(Long id);
}
