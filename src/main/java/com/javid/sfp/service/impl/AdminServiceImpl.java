package com.javid.sfp.service.impl;

import com.javid.sfp.exception.BadRequestException;
import com.javid.sfp.exception.ResourceNotFoundException;
import com.javid.sfp.mapper.AdminMapper;
import com.javid.sfp.model.Admin;
import com.javid.sfp.repository.AdminRepository;
import com.javid.sfp.security.Role;
import com.javid.sfp.service.AdminService;
import com.javid.sfp.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author javid
 * Created on 5/9/2022
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final UserService userService;

    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper, UserService userService) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
        this.userService = userService;
    }

    @Override
    public Admin findByID(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exists!"));
    }

    @Override
    public void deleteByID(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not exists!"));
    }

    @Override
    public Admin creat(Admin admin) {
        if (userService.existsByEmail(admin.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        admin.getRoles().add(Role.ADMIN);
        userService.encodePassword(admin);
        return adminRepository.save(admin);
    }

    @Override
    public void update(Admin admin) {
        var optional = adminRepository.findById(admin.getId());
        if (optional.isPresent()) {
            var fetched = optional.get();
            var email = admin.getEmail();
            if (!fetched.getEmail().equalsIgnoreCase(email) && userService.existsByEmail(email)) {
                throw new BadRequestException("Email already exists");
            }
            admin.setPassword(fetched.getPassword());
            adminRepository.save(admin);
        }
    }
}
