package com.javid.sfp.controller.api.v1;

import com.javid.sfp.dto.ExpertDto;
import com.javid.sfp.event.OnRegistrationCompleteEvent;
import com.javid.sfp.mapper.ExpertMapper;
import com.javid.sfp.service.ExpertService;
import com.javid.sfp.util.AdvanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author javid
 * Created on 6/1/2022
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ExpertRestController {

    private final ExpertService expertService;
    private final ExpertMapper expertMapper;
    private final ApplicationEventPublisher publisher;
    private final MessageSource messages;

    public ExpertRestController(ExpertService expertService, ExpertMapper expertMapper
            , ApplicationEventPublisher publisher, MessageSource messages) {
        this.expertService = expertService;
        this.expertMapper = expertMapper;
        this.publisher = publisher;
        this.messages = messages;
    }

    @GetMapping("expert")
    @ResponseBody
    public List<ExpertDto> getAll() {
        return expertMapper.mapToDto(expertService.findAll());
    }

    @GetMapping("expert/{id}")
    @ResponseBody
    public ExpertDto getById(@PathVariable(name = "id") String id) {
        return expertMapper.mapToDto(expertService.findById(Long.valueOf(id)));
    }

    @PostMapping(value = "expert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> create(@Validated(AdvanceInfo.class) @ModelAttribute ExpertDto expertDto, HttpServletRequest request) {
        var expert = expertMapper.mapToEntity(expertDto);
        expert.setExpertImage(multipartToByteArray(expertDto.getImage()));

        var user = expertService.create(expert);
        var local = request.getLocale();
        publisher.publishEvent(new OnRegistrationCompleteEvent(user, local, request.getContextPath()));
        var message = messages.getMessage("msg.reg.success", null, local);

        return Map.of("message", message, "redirect", "/index");
    }

    @PutMapping(value = "expert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@Validated(AdvanceInfo.class) @ModelAttribute ExpertDto expertDto) {
        if (expertDto.getId() == null) {
            return;
        }
        var expert = expertMapper.mapToEntity(expertDto);
        expert.setExpertImage(multipartToByteArray(expertDto.getImage()));
        expertService.update(expert);
    }

    @DeleteMapping("expert")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathParam("id") String id) {
        expertService.deleteByID(Long.valueOf(id));
    }

    /**
     * Converts multipart file to Byte array
     *
     * @param file multipart file
     * @return Byte array of file
     */
    private Byte[] multipartToByteArray(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                var bytes = file.getBytes();
                var byteObjects = new Byte[bytes.length];
                for (int i = 0; i < bytes.length; i++) {
                    byteObjects[i] = bytes[i];
                }
                return byteObjects;
            } catch (IOException e) {
                log.error("Image converting error ", e);
            }
        }
        return new Byte[0];
    }

}
