package com.javid.sfp.controller.api.v1;

import com.javid.sfp.dto.ExpertOfferDto;
import com.javid.sfp.mapper.ExpertOfferMapper;
import com.javid.sfp.model.ExpertOfferId;
import com.javid.sfp.service.ExpertOfferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author javid
 * Created on 6/2/2022
 */
@RestController
@RequestMapping("/api/v1")
public class OfferRestController {

    private final ExpertOfferService offerService;
    private final ExpertOfferMapper offerMapper;

    public OfferRestController(ExpertOfferService offerService, ExpertOfferMapper offerMapper) {
        this.offerService = offerService;
        this.offerMapper = offerMapper;
    }

    @GetMapping("offers")
    @ResponseBody
    public List<ExpertOfferDto> getAll() {
        return offerMapper.mapToDto(offerService.findAll());
    }

    @GetMapping("offer")
    @ResponseBody
    public ExpertOfferDto getById(@PathParam("expert") String expertId, @PathParam("order") String orderId) {
        var id = new ExpertOfferId();
        id.setExpertId(Long.valueOf(expertId));
        id.setCustomerOrderId(Long.valueOf(orderId));

        return offerMapper.mapToDto(offerService.findById(id));
    }


}
