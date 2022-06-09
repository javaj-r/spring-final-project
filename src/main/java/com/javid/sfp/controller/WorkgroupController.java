package com.javid.sfp.controller;

import com.javid.sfp.mapper.WorkMapper;
import com.javid.sfp.mapper.WorkgroupMapper;
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

    private final WorkgroupService workgroupService;
    private final WorkgroupMapper workgroupMapper;
    private final WorkService workService;
    private final WorkMapper workMapper;

    public WorkgroupController(WorkgroupService workgroupService, WorkgroupMapper workgroupMapper,
                               WorkService workService, WorkMapper workMapper) {
        this.workgroupService = workgroupService;
        this.workgroupMapper = workgroupMapper;
        this.workService = workService;
        this.workMapper = workMapper;
    }

    @GetMapping("{id}")
    public String getWorkgroupWorks(@PathVariable(name = "id") String id, Model model) {
        var workgroupId = Long.valueOf(id);
        var workgroup = workgroupMapper.mapToDto(workgroupService.findById(workgroupId));
        var works = workMapper.mapToDto(workService.findAllByWorkgroupId(workgroupId));

        model.addAttribute("workgroup", workgroup);
        model.addAttribute("works", works);

        return "works";
    }
}
