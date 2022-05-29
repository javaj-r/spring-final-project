package com.javid.sfp.controller;

import com.javid.sfp.service.WorkgroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model) {
        var workgroups = workgroupService.findAll();
        model.addAttribute("workgroups", workgroups);

        return "index";
    }

}
