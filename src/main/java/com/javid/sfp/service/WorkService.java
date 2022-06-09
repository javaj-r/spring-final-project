package com.javid.sfp.service;

import com.javid.sfp.model.Work;

import java.util.List;

/**
 * @author javid
 * Created on 5/23/2022
 */
public interface WorkService {

    List<Work> findAllByWorkgroupId(Long id);

    List<Work> findAll();

    Work findById(Long id);

    Work create(Work work, Long workgroupId);

    void update(Work work);

    void delete(Long id);
}
