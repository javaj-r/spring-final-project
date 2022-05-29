package com.javid.sfp.service.impl;

import com.javid.sfp.dto.WorkgroupDto;
import com.javid.sfp.exception.ResourceNotFoundException;
import com.javid.sfp.mapper.WorkgroupMapper;
import com.javid.sfp.repository.WorkgroupRepository;
import com.javid.sfp.service.WorkgroupService;
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

    @Override
    public WorkgroupDto findById(Long id) {
        return workgroupRepository.findById(id)
                .map(workgroupMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Workgroup not found!"));
    }
}
