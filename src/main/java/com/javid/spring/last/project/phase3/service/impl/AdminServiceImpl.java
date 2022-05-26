package com.javid.spring.last.project.phase3.service.impl;

import com.javid.spring.last.project.phase3.dto.AdminDto;
import com.javid.spring.last.project.phase3.exception.ResourceNotFoundException;
import com.javid.spring.last.project.phase3.mapper.AdminMapper;
import com.javid.spring.last.project.phase3.repository.AdminRepository;
import com.javid.spring.last.project.phase3.service.AdminService;
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
