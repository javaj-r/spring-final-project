package com.javid.sfp.controller;

import com.javid.sfp.dto.ExpertDto;
import com.javid.sfp.model.enums.UserStatus;
import com.javid.sfp.service.ExpertService;
import com.javid.sfp.service.UserService;
import com.javid.sfp.util.AdvanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author javid
 * Created on 5/21/2022
 */
@Slf4j
@Controller
@RequestMapping("expert")
public class ExpertController {

    public static final String EXPERT = "expert";
    public static final String EXPERT_FORM = "expert/form";
    private final ExpertService expertService;
    private final UserService userService;

    public ExpertController(ExpertService expertService, UserService userService) {
        this.expertService = expertService;
        this.userService = userService;
    }

    @GetMapping("register")
    public String getRegisterPage(Model model) {
        model.addAttribute(ExpertController.EXPERT, new ExpertDto());

        return EXPERT_FORM;
    }

    @PostMapping(value = "register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String register(@Validated(AdvanceInfo.class) @ModelAttribute(name = EXPERT) ExpertDto expert, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors()
                    .parallelStream()
                    .forEach(objectError -> log.debug(objectError.getDefaultMessage()));

            return EXPERT_FORM;
        }
        if (userService.existsByEmail(expert.getEmail())) {
            result.addError(new ObjectError("email", "Email already exists"));
            log.debug("Duplicate email: " + expert.getEmail());

            return EXPERT_FORM;
        }
        if (!expert.getImage().isEmpty()) {
            try {
                var bytes = expert.getImage().getBytes();
                var byteObjects = new Byte[bytes.length];
                for (int i = 0; i < bytes.length; i++) {
                    byteObjects[i] = bytes[i];
                }
                expert.setExpertImage(byteObjects);
            } catch (IOException e) {
                log.error("Image converting error ", e);

                return EXPERT_FORM;
            }
        }
        expert.setExpertStatus(UserStatus.NEW);
        expertService.saveOrUpdate(expert);

        return "redirect:/index";
    }

    @GetMapping("search")
    public String initSearchForm(Model model) {
        model.addAttribute(EXPERT, new ExpertDto());
        model.addAttribute("experts", new ArrayList<ExpertDto>());

        return "expert/search";
    }

    @PostMapping("search")
    public ModelAndView processSearchForm(@ModelAttribute ExpertDto expert) {
        var experts = expertService.findAllByCondition(expert);

        var modelView = new ModelAndView("expert/search");
        modelView.addObject(EXPERT, expert);
        modelView.addObject("experts", experts);

        return modelView;
    }


}
