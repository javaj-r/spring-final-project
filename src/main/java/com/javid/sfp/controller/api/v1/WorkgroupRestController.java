package com.javid.sfp.controller.api.v1;

import com.javid.sfp.dto.WorkgroupDto;
import com.javid.sfp.mapper.WorkgroupMapper;
import com.javid.sfp.service.WorkgroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author javid
 * Created on 5/23/2022
 */
@RestController
@RequestMapping("/api/v1")
public class WorkgroupRestController {

    private final WorkgroupService workgroupService;
    private final WorkgroupMapper workgroupMapper;

    public WorkgroupRestController(WorkgroupService workgroupService, WorkgroupMapper workgroupMapper) {
        this.workgroupService = workgroupService;
        this.workgroupMapper = workgroupMapper;
    }

    @GetMapping("workgroup")
    @ResponseBody
    public List<WorkgroupDto> getAll() {
        return workgroupMapper.mapToDto(workgroupService.findAll());
    }

    @GetMapping("workgroup/{id}")
    @ResponseBody
    public WorkgroupDto getById(@PathVariable(name = "id") String id) {
        return workgroupMapper.mapToDto(workgroupService.findById(Long.valueOf(id)));
    }

    @PostMapping("workgroup")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkgroupDto create(@Valid @NotBlank @RequestParam String name) {
        return workgroupMapper.mapToDto(workgroupService.create(name));
    }

    @PutMapping("workgroup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody WorkgroupDto workgroupDto) {
        if (workgroupDto.getId() == null) {
            return;
        }
        workgroupService.update(workgroupMapper.mapToEntity(workgroupDto));
    }

    @DeleteMapping("workgroup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathParam("id") String id) {
        workgroupService.delete(Long.valueOf(id));
    }

}
