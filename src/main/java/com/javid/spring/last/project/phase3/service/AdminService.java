package com.javid.spring.last.project.phase3.service;

import com.javid.spring.last.project.phase3.dto.AdminDto;

/**
 * @author javid
 * Created on 5/9/2022
 */
public interface AdminService {

    AdminDto findByID(Long id);

    AdminDto findByEmailAndPassword(String email, String password);

    AdminDto save(AdminDto adminDto);

    AdminDto save(AdminDto adminDto, Long id);

    void deleteByID(Long id);

}
