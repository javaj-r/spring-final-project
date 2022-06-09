package com.javid.sfp.service.impl;

import com.javid.sfp.exception.BadRequestException;
import com.javid.sfp.exception.ResourceNotFoundException;
import com.javid.sfp.model.Workgroup;
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

    public WorkgroupServiceImpl(WorkgroupRepository workgroupRepository) {
        this.workgroupRepository = workgroupRepository;
    }

    @Override
    public List<Workgroup> findAll() {
        return workgroupRepository.findAll();
    }

    @Override
    public Workgroup findById(Long id) {
        return workgroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workgroup not found!"));
    }

    @Override
    public Workgroup create(String name) {
        if (workgroupRepository.existsByName(name)) {
            throw new BadRequestException("Workgroup " + name + " already exists!");
        }

        var workgroup = new Workgroup();
        workgroup.setName(name);

        return workgroupRepository.save(workgroup);
    }

    @Override
    public void update(Workgroup workgroup) {
        if (workgroupRepository.findById(workgroup.getId()).isPresent()) {
            workgroupRepository.save(workgroup);
        }
    }

    @Override
    public void delete(Long id) {
        workgroupRepository.deleteById(id);
    }
}
