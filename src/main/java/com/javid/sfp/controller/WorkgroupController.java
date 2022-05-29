package com.javid.sfp.controller;

import com.javid.sfp.service.WorkService;
import com.javid.sfp.service.WorkgroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author javid
 * Created on 5/23/2022
 */
@Controller
@RequestMapping("workgroup")
public class WorkgroupController {

    private final WorkgroupService workgroupService1;
    private final WorkService workgroupService;

    public WorkgroupController(WorkgroupService workgroupService1, WorkService workgroupService) {
        this.workgroupService1 = workgroupService1;
        this.workgroupService = workgroupService;
    }

    @GetMapping("{id}")
    public String getWorkgroupWorks(@PathVariable(name = "id") String id, Model model) {
        var workgroupId = Long.valueOf(id);
        var workgroup = workgroupService1.findById(workgroupId);
        var works = workgroupService.findAllByWorkgroupId(workgroupId);

        model.addAttribute("workgroup", workgroup);
        model.addAttribute("works", works);

        return "works";
    }
}
