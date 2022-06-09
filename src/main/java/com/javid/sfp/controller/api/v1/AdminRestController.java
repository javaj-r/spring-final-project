package com.javid.sfp.controller.api.v1;

import com.javid.sfp.dto.AdminDto;
import com.javid.sfp.event.OnRegistrationCompleteEvent;
import com.javid.sfp.mapper.AdminMapper;
import com.javid.sfp.service.AdminService;
import com.javid.sfp.util.AdvanceInfo;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.Map;

/**
 * @author javid
 * Created on 5/30/2022
 */
@RestController
@RequestMapping("/api/v1")
public class AdminRestController {

    private final AdminService adminService;
    private final AdminMapper adminMapper;
    private final ApplicationEventPublisher publisher;
    private final MessageSource messages;

    public AdminRestController(AdminService adminService, AdminMapper adminMapper, ApplicationEventPublisher publisher, MessageSource messages) {
        this.adminService = adminService;
        this.adminMapper = adminMapper;
        this.publisher = publisher;
        this.messages = messages;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/info/current")
    public AdminDto getAdminInfo(Principal principal) {
        var dto = adminMapper.mapToDto(adminService.findByEmail(principal.getName()));
        dto.setPassword("");
        return dto;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("admin/{id}")
    public AdminDto getAdminInfo(@PathVariable String id) {
        var dto = adminMapper.mapToDto(adminService.findByID(Long.valueOf(id)));
        dto.setPassword("");
        return dto;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> create(@Validated(AdvanceInfo.class) @ModelAttribute AdminDto adminDto, HttpServletRequest request) {
        var admin = adminMapper.mapToEntity(adminDto);
        var user = adminService.creat(admin);
        var local = request.getLocale();
        publisher.publishEvent(new OnRegistrationCompleteEvent(user, local, request.getContextPath()));
        var message = messages.getMessage("msg.reg.success", null, local);

        return Map.of("message", message, "redirect", "/index");
    }

    @PutMapping(value = "admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@Validated(AdvanceInfo.class) @ModelAttribute AdminDto adminDto) {
        if (adminDto.getId() == null) {
            return;
        }
        var admin = adminMapper.mapToEntity(adminDto);
        adminService.update(admin);
    }

    @DeleteMapping("admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathParam("id") String id) {
        adminService.deleteByID(Long.valueOf(id));
    }

}
