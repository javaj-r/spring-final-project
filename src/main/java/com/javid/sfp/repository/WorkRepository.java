package com.javid.sfp.repository;

import com.javid.sfp.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author javid
 * Created on 5/8/2022
 */
public interface WorkRepository extends JpaRepository<Work, Long> {

    Optional<Work> findByName(String name);

    List<Work> findAllByWorkgroupId(Long id);

    boolean existsByName(String name);
}
