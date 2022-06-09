package com.javid.sfp.controller.api.v1;

import com.javid.sfp.dto.CustomerOrderDto;
import com.javid.sfp.mapper.CustomerOrderMapper;
import com.javid.sfp.model.Customer;
import com.javid.sfp.model.Work;
import com.javid.sfp.service.CustomerOrderService;
import com.javid.sfp.util.AdvanceInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author javid
 * Created on 6/2/2022
 */
@RestController
@RequestMapping("/api/v1")
public class OrderRestController {

    private final CustomerOrderService orderService;
    private final CustomerOrderMapper orderMapper;

    public OrderRestController(CustomerOrderService orderService, CustomerOrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("order")
    @ResponseBody
    public List<CustomerOrderDto> getAll() {
        return orderMapper.mapToDto(orderService.findAll());
    }

    @GetMapping("order/{id}")
    @ResponseBody
    public CustomerOrderDto getById(@PathVariable(name = "id") String id) {
        return orderMapper.mapToDto(orderService.findById(Long.valueOf(id)));
    }

    @GetMapping("customer/{id}/orders")
    @ResponseBody
    public List<CustomerOrderDto> getAllByCustomerId(@PathVariable(name = "id") String id) {
        return orderMapper.mapToDto(orderService.findAllByCustomerId(Long.valueOf(id)));
    }

    @GetMapping("expert/{id}/orders")
    @ResponseBody
    public List<CustomerOrderDto> getAllByExpertId(@PathVariable(name = "id") String id) {
        return orderMapper.mapToDto(orderService.findAllByExpertId(Long.valueOf(id)));
    }

    @GetMapping(value = "expert/{id}/orders/available")
    @ResponseBody
    public List<CustomerOrderDto> getAllAvailableByExpertId(@PathVariable(name = "id") String id) {
        return orderMapper.mapToDto(orderService.findAllByCustomerId(Long.valueOf(id)));
    }

    @PostMapping(value = "order", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerOrderDto create(@Validated(AdvanceInfo.class) @ModelAttribute CustomerOrderDto orderDto) {
        var customer = new Customer();
        customer.setId(orderDto.getCustomerId());
        var work = new Work();
        work.setId(orderDto.getWorkId());

        var order = orderMapper.mapToEntity(orderDto);
        order.setCustomer(customer);
        order.setWork(work);

        return orderMapper.mapToDto(orderService.create(order));
    }

}
