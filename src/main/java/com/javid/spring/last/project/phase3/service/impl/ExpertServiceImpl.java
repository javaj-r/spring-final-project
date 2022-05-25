package com.javid.spring.last.project.phase3.service.impl;

import com.javid.spring.last.project.phase3.dto.ExpertDto;
import com.javid.spring.last.project.phase3.exception.ResourceNotFoundException;
import com.javid.spring.last.project.phase3.mapper.ExpertMapper;
import com.javid.spring.last.project.phase3.repository.ExpertRepository;
import com.javid.spring.last.project.phase3.service.ExpertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author javid
 * Created on 5/12/2022
 */
@Slf4j
@Service
@Transactional
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;
    private final ExpertMapper expertMapper;

    public ExpertServiceImpl(ExpertRepository expertRepository, ExpertMapper expertMapper) {
        this.expertRepository = expertRepository;
        this.expertMapper = expertMapper;
    }

    @Override
    public ExpertDto findByID(Long id) {
        return expertRepository.findById(id)
                .map(expertMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not exists!"));
    }

    @Override
    public ExpertDto findByEmailAndPassword(String email, String password) {
        return expertRepository.findByEmailAndPassword(email, password)
                .map(expertMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Wrong Email or Password!"));
    }

    @Override
    public ExpertDto saveOrUpdate(ExpertDto expertDto) {
        log.debug("RecipeServiceImpl: saveRecipeCommand");

        var expert = expertMapper.mapToEntity(expertDto);
        var savedExpert = expert != null ? expertRepository.save(expert) : null;
        log.debug(savedExpert != null ? "Saved Expert: " + savedExpert.getId() : "Saved Expert is null!");

        return expertMapper.mapToDto(savedExpert);
    }

    @Override
    public void deleteByID(Long id) {
        expertRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return expertRepository.existsByEmail(email);
    }
}