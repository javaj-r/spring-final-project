package com.javid.sfp.service.impl;

import com.javid.sfp.dto.AdminDto;
import com.javid.sfp.exception.ResourceNotFoundException;
import com.javid.sfp.mapper.AdminMapper;
import com.javid.sfp.repository.AdminRepository;
import com.javid.sfp.service.AdminService;
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

    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminDto findByID(Long id) {
        return adminRepository.findById(id)
                .map(adminMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not exists!"));
    }

    @Override
    public AdminDto findByEmailAndPassword(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password)
                .map(adminMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Wrong Email or Password!"));
    }

    @Override
    public AdminDto save(AdminDto adminDto) {
        return save(adminDto, null);
    }

    @Override
    public AdminDto save(AdminDto adminDto, Long id) {
        var admin = adminMapper.mapToEntity(adminDto);
        admin.setId(id);

        return adminMapper.mapToDto(adminRepository.save(admin));
    }

    @Override
    public void deleteByID(Long id) {
        adminRepository.deleteById(id);
    }
}
