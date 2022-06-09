package com.javid.sfp.service;

import com.javid.sfp.model.base.User;

/**
 * @author javid
 * Created on 5/24/2022
 */
public interface UserService {

    boolean existsByEmail(String email);

    void update(User user);

    void encodePassword(User user);
}
