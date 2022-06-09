package com.javid.sfp.controller;

import com.javid.sfp.dto.ExpertOfferDto;
import com.javid.sfp.mapper.CustomerOrderMapper;
import com.javid.sfp.mapper.ExpertOfferMapper;
import com.javid.sfp.model.ExpertOfferId;
import com.javid.sfp.service.CustomerOrderService;
import com.javid.sfp.service.ExpertOfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author javid
 * Created on 6/8/2022
 */
@Controller
public class OrderController {

    private final CustomerOrderService orderService;
    private final CustomerOrderMapper orderMapper;
    private final ExpertOfferService offerService;
    private final ExpertOfferMapper offerMapper;

    public OrderController(CustomerOrderService orderService, CustomerOrderMapper orderMapper,
                           ExpertOfferService offerService, ExpertOfferMapper offerMapper
                           ) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.offerService = offerService;
        this.offerMapper = offerMapper;
    }

    @GetMapping("/customer/{customerId}/order/{orderId}")
    public String initOrderPage(@PathVariable String customerId, @PathVariable String orderId, Model model) {

        var order = orderService.findByIdAndCustomerId(Long.valueOf(orderId), Long.valueOf(customerId));
        var customer = order.getCustomer();

        var customerName = customer.getFirstname() + " " + customer.getLastname();
        var expertName = "No Expert Selected yet";

        var orderDto = orderMapper.mapToDto(order);
        orderDto.setCustomerId(customer.getId());

        ExpertOfferDto selectedOffer = null;
        List<ExpertOfferDto> offers = null;

        if (order.getSelectedExpert() != null) {
            var expert = order.getSelectedExpert();
            orderDto.setSelectedExpertId(expert.getId());
            expertName = expert.getFirstname() + " " + expert.getLastname();
            selectedOffer = offerMapper.mapToDto(offerService.findById(new ExpertOfferId(expert.getId(), order.getId())));
        } else {
            offers = offerMapper.mapToDto(offerService.findAllByOrderId(order.getId()));
        }

        model.addAttribute("customerName", customerName);
        model.addAttribute("expertName", expertName);
        model.addAttribute("offer", selectedOffer);
        model.addAttribute("offers", offers);
        model.addAttribute("order", orderDto);

        return "order/show";
    }

}
