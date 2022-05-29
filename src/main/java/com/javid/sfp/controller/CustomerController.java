package com.javid.sfp.controller;

import com.javid.sfp.dto.CustomerDto;
import com.javid.sfp.service.CustomerService;
import com.javid.sfp.service.UserService;
import com.javid.sfp.util.AdvanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

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
    public String initCreationForm(Model model) {
        model.addAttribute("customer", new CustomerDto());

        return CUSTOMER_FORM;
    }

    @PostMapping(value = "register")
    public String processCreationForm(@Validated(AdvanceInfo.class) @ModelAttribute(name = "customer") CustomerDto customer, BindingResult result) {
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

    @GetMapping("search")
    public String initSearchForm(Model model) {
        model.addAttribute("customer", new CustomerDto());
        model.addAttribute("customers", new ArrayList<CustomerDto>());

        return "customer/search";
    }

    @PostMapping("search")
    public ModelAndView processSearchForm(@ModelAttribute CustomerDto customer) {
        var customers = customerService.findAllByNameAndEmail(customer);

        var view = new ModelAndView("customer/search");
        view.addObject("customer", customer);
        view.addObject("customers", customers);

        return view;
    }
}
