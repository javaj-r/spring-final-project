package com.javid.spring.last.project.phase3.service.impl;

import com.javid.spring.last.project.phase3.dto.WorkDto;
import com.javid.spring.last.project.phase3.mapper.WorkMapper;
import com.javid.spring.last.project.phase3.repository.WorkRepository;
import com.javid.spring.last.project.phase3.service.WorkService;
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
