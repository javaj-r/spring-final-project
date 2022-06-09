package com.javid.sfp.service;

import com.javid.sfp.model.Admin;

/**
 * @author javid
 * Created on 5/9/2022
 */
public interface AdminService {

    Admin findByID(Long id);

    void deleteByID(Long id);

    Admin findByEmail(String email);

    Admin creat(Admin admin);

    void update(Admin admin);
}
