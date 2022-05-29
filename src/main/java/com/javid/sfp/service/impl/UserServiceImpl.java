package com.javid.sfp.service.impl;

import com.javid.sfp.repository.UserRepository;
import com.javid.sfp.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author javid
 * Created on 5/24/2022
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
