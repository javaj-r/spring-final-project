package com.javid.sfp.service.impl;

import com.javid.sfp.dto.ExpertDto;
import com.javid.sfp.exception.BadRequestException;
import com.javid.sfp.exception.ResourceNotFoundException;
import com.javid.sfp.mapper.ExpertMapper;
import com.javid.sfp.model.Expert;
import com.javid.sfp.model.Work;
import com.javid.sfp.repository.ExpertRepository;
import com.javid.sfp.repository.specification.ExpertSpecification;
import com.javid.sfp.repository.specification.SearchCriteria;
import com.javid.sfp.security.Role;
import com.javid.sfp.service.ExpertService;
import com.javid.sfp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    private final UserService userService;

    public ExpertServiceImpl(ExpertRepository expertRepository, ExpertMapper expertMapper, UserService userService) {
        this.expertRepository = expertRepository;
        this.expertMapper = expertMapper;
        this.userService = userService;
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
    public Expert create(Expert expert) {
        if (userService.existsByEmail(expert.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        expert.getRoles().add(Role.EXPERT);
        userService.encodePassword(expert);
        return expertRepository.save(expert);
    }

    @Override
    public ExpertDto saveOrUpdate(ExpertDto expertDto) {
        log.debug("RecipeServiceImpl: saveRecipeCommand");

        var expert = expertMapper.mapToEntity(expertDto);
        var savedExpert = expert != null ? expertRepository.save(expert) : null;
        userService.encodePassword(expert);
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

    @Override
    public List<Expert> findAllByCondition(Expert expert, String enrolledWorkName) {
        var firstname = expert.getFirstname() == null ? "" : expert.getFirstname();
        var lastname = expert.getLastname() == null ? "" : expert.getLastname();
        var email = expert.getEmail() == null ? "" : expert.getEmail();
        var workName = enrolledWorkName == null ? "" : enrolledWorkName;

        var criteria = SearchCriteria.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .workName(workName)
                .score(expert.getScore())
                .build();

        var spec = new ExpertSpecification(criteria);

        return expertRepository.findAll(spec);
    }

    @Override
    public List<Expert> findAll() {
        return expertRepository.findAll();
    }

    @Override
    public Expert findById(Long id) {
        return expertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expert not found!"));
    }

    @Override
    public void update(Expert expert) {
        var optional = expertRepository.findById(expert.getId());
        if (optional.isPresent()) {
            var fetched = optional.get();
            var email = expert.getEmail();
            if (!fetched.getEmail().equalsIgnoreCase(email) && userService.existsByEmail(email)) {
                throw new BadRequestException("Email already exists");
            }
            userService.encodePassword(expert);
            expertRepository.save(expert);
        }
    }

    @Override
    public List<Work> findExpertEnrolledWorks(Long expertId) {
        return new ArrayList<>(expertRepository.findById(expertId)
                .orElseThrow(() -> new ResourceNotFoundException("Expert not found!"))
                .getEnrolledWorks());
    }
}
