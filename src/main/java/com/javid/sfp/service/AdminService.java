package com.javid.sfp.service;

import com.javid.sfp.dto.AdminDto;

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
