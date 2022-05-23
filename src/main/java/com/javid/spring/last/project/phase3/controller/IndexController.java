package com.javid.spring.last.project.phase3.controller;

import com.javid.spring.last.project.phase3.service.WorkgroupService;
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
