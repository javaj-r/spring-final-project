package com.javid.sfp.service.impl;

import com.javid.sfp.dto.WorkDto;
import com.javid.sfp.mapper.WorkMapper;
import com.javid.sfp.repository.WorkRepository;
import com.javid.sfp.service.WorkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author javid
 * Created on 5/23/2022
 */
@Service
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;
    private final WorkMapper workMapper;

    public WorkServiceImpl(WorkRepository workRepository, WorkMapper workMapper) {
        this.workRepository = workRepository;
        this.workMapper = workMapper;
    }

    @Override
    public List<WorkDto> findAllByWorkgroupId(Long id) {
        return workMapper.mapToDto(workRepository.findAllByWorkgroupId(id));
    }
}
