package com.javid.spring.last.project.phase3.service.impl;

import com.javid.spring.last.project.phase3.dto.WorkgroupDto;
import com.javid.spring.last.project.phase3.mapper.WorkgroupMapper;
import com.javid.spring.last.project.phase3.repository.WorkgroupRepository;
import com.javid.spring.last.project.phase3.service.WorkgroupService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author javid
 * Created on 5/23/2022
 */
@Service
public class WorkgroupServiceImpl implements WorkgroupService {

    private final WorkgroupRepository workgroupRepository;
    private final WorkgroupMapper workgroupMapper;

    public WorkgroupServiceImpl(WorkgroupRepository workgroupRepository, WorkgroupMapper workgroupMapper) {
        this.workgroupRepository = workgroupRepository;
        this.workgroupMapper = workgroupMapper;
    }

    @Override
    public List<WorkgroupDto> findAll() {
        return workgroupMapper.mapToDto(workgroupRepository.findAll());
    }
}