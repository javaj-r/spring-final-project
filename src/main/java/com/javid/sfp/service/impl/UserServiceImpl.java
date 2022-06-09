package com.javid.sfp.service.impl;

import com.javid.sfp.exception.BadRequestException;
import com.javid.sfp.model.base.User;
import com.javid.sfp.repository.UserRepository;
import com.javid.sfp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author javid
 * Created on 5/24/2022
 */
@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }

        return optionalUser.get();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void update(User user) {
        var optional = userRepository.findById(user.getId());
        if (optional.isPresent()) {
            var fetched = optional.get();
            var email = user.getEmail();
            if (!fetched.getEmail().equals(email) && existsByEmail(email)) {
                throw new BadRequestException("Email already exists");
            }
            userRepository.save(user);
        }
    }

    @Override
    public void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
