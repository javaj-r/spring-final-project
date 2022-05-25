package com.javid.spring.last.project.phase3.service.impl;

import com.javid.spring.last.project.phase3.repository.UserRepository;
import com.javid.spring.last.project.phase3.service.UserService;
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
