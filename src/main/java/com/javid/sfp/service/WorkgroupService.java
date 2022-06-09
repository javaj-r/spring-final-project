package com.javid.sfp.service;

import com.javid.sfp.model.Workgroup;

import java.util.List;

/**
 * @author javid
 * Created on 5/21/2022
 */
public interface WorkgroupService {

    List<Workgroup> findAll();

    Workgroup findById(Long id);

    Workgroup create(String name);

    void update(Workgroup workgroup);

    void delete(Long id);
}
