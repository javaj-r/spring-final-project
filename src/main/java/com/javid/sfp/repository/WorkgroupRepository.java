package com.javid.sfp.repository;

import com.javid.sfp.model.Workgroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author javid
 * Created on 5/8/2022
 */
public interface WorkgroupRepository extends JpaRepository<Workgroup, Long> {

    Optional<Workgroup> findByName(String name);
}
