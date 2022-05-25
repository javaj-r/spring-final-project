package com.javid.spring.last.project.phase3.controller;

import com.javid.spring.last.project.phase3.dto.CustomerDto;
import com.javid.spring.last.project.phase3.service.CustomerService;
import com.javid.spring.last.project.phase3.service.UserService;
import com.javid.spring.last.project.phase3.util.AdvanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author javid
 * Created on 5/21/2022
 */
@Slf4j
@Controller
@RequestMapping("customer")
public class CustomerController {

    public static final String CUSTOMER_FORM = "customer/form";
    private final CustomerService customerService;
    private final UserService userService;

    public CustomerController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping("register")
    public String getRegisterPage(Model model) {
        model.addAttribute("customer", new CustomerDto());

        return CUSTOMER_FORM;
    }

    @PostMapping(value = "register"/*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    public String register(@Validated(AdvanceInfo.class) @ModelAttribute(name = "customer") CustomerDto customer, BindingResult result) {
        if (result.hasErrors()) {
            result
                    .getAllErrors()
                    .parallelStream()
                    .forEach(objectError -> log.debug(objectError.getDefaultMessage()));
            return CUSTOMER_FORM;
        }
        if (userService.existsByEmail(customer.getEmail())) {
            result.rejectValue("email", "error.customer", "An account already exists for this email.");
            log.debug("Duplicate email: " + customer.getEmail());
            return CUSTOMER_FORM;
        }
        customerService.saveOrUpdate(customer);

        return "redirect:/index";
    }

}
