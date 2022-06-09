package com.javid.sfp.controller.api.v1;

import com.javid.sfp.dto.WorkDto;
import com.javid.sfp.mapper.WorkMapper;
import com.javid.sfp.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author javid
 * Created on 6/1/2022
 */
@RestController
@RequestMapping("/api/v1")
public class WorkRestController {

    private final WorkService workService;
    private final WorkMapper workMapper;

    public WorkRestController(WorkService workService, WorkMapper workMapper) {
        this.workService = workService;
        this.workMapper = workMapper;
    }

    @GetMapping("work")
    @ResponseBody
    public List<WorkDto> getAll() {
        return workMapper.mapToDto(workService.findAll());
    }

    @GetMapping("work/{id}")
    @ResponseBody
    public WorkDto getById(@PathVariable(name = "id") String id) {
        return workMapper.mapToDto(workService.findById(Long.valueOf(id)));
    }

    @GetMapping("{groupId}/work")
    @ResponseBody
    public List<WorkDto> getAllByGroupId(@PathVariable(name = "groupId") String id) {
        return workMapper.mapToDto(workService.findAllByWorkgroupId(Long.valueOf(id)));
    }

    @PostMapping("work")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkDto create(@Valid @RequestBody WorkDto workDto) {
        var work = workMapper.mapToEntity(workDto);
        return workMapper.mapToDto(workService.create(work, workDto.getWorkgroupId()));
    }

    @PutMapping("work")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody WorkDto workDto) {
        if (workDto.getId() == null) {
            return;
        }
        workService.update(workMapper.mapToEntity(workDto));
    }

    @DeleteMapping("work")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathParam("id") String id) {
        workService.delete(Long.valueOf(id));
    }

}
