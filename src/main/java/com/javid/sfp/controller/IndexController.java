package com.javid.sfp.controller;

import com.javid.sfp.service.WorkgroupService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

/**
 * @author javid
 * Created on 5/21/2022
 */
@Controller
public class IndexController {

    private final WorkgroupService workgroupService;

    public IndexController(WorkgroupService workgroupService) {
        this.workgroupService = workgroupService;
    }

    /**
     * Deprecated no need to set model on index page since using ajax
     * @param model {@link Model}
     * @return html page name
     */
    @Deprecated(since = "1.0", forRemoval = true)
    @GetMapping({"", "/", "index", "index.html"})
    public String index(Model model) {
        var workgroups = workgroupService.findAll();
        model.addAttribute("workgroups", workgroups);

        return "index";
    }

    @GetMapping("welcome")
    public String initWelcomePage(Authentication authentication) {
        var authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        if (authorities.contains("ROLE_ADMIN")) {
            return "redirect:/swagger";
        }

        return "index";
    }
}
