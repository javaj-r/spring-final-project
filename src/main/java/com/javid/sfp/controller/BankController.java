package com.javid.sfp.controller;

import com.javid.sfp.model.VerificationToken;
import com.javid.sfp.service.CustomerService;
import com.javid.sfp.service.VerificationTokenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

/**
 * @author javid
 * Created on 6/9/2022
 */
@Controller
public class BankController {

    private final CustomerService customerService;
    private final VerificationTokenService tokenService;

    public BankController(CustomerService customerService, VerificationTokenService tokenService) {
        this.customerService = customerService;
        this.tokenService = tokenService;
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("bank")
    public String initBankFormPage(Authentication user, Model model) {
        var customer = customerService.findByEmail(user.getName());
        var token = UUID.randomUUID().toString();
        tokenService.saveOrUpdate(new VerificationToken(customer, token), 10);
        model.addAttribute("token", token);

        return "bank/form";
    }
}
