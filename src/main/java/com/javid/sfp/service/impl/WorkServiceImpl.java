package com.javid.sfp.service.impl;

import com.javid.sfp.exception.BadRequestException;
import com.javid.sfp.exception.ResourceNotFoundException;
import com.javid.sfp.model.Work;
import com.javid.sfp.repository.WorkRepository;
import com.javid.sfp.service.WorkService;
import com.javid.sfp.service.WorkgroupService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author javid
 * Created on 5/23/2022
 */
@Service
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;
    private final WorkgroupService workgroupService;

    public WorkServiceImpl(WorkRepository workRepository, WorkgroupService workgroupService) {
        this.workRepository = workRepository;
        this.workgroupService = workgroupService;
    }

    @Override
    public List<Work> findAllByWorkgroupId(Long id) {
        return workRepository.findAllByWorkgroupId(id);
    }

    @Override
    public List<Work> findAll() {
        return workRepository.findAll();
    }

    @Override
    public Work findById(Long id) {
        return workRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work not found!"));
    }

    @Override
    public Work create(Work work, Long workgroupId) {
        var name = work.getName();
        if (workRepository.existsByName(name)) {
            throw new BadRequestException("Work " + name + " already exists!");
        }

        var group = workgroupService.findById(workgroupId);
        work.setWorkgroup(group);

        return workRepository.save(work);
    }

    @Override
    public void update(Work work) {
        if (workRepository.findById(work.getId()).isPresent()) {
            workRepository.save(work);
        }
    }

    @Override
    public void delete(Long id) {
        workRepository.deleteById(id);
    }
}
