package com.javid.sfp.controller.api.v1;

import com.javid.sfp.dto.CustomerDto;
import com.javid.sfp.event.OnRegistrationCompleteEvent;
import com.javid.sfp.exception.BadRequestException;
import com.javid.sfp.mapper.CustomerMapper;
import com.javid.sfp.service.CustomerService;
import com.javid.sfp.service.VerificationTokenService;
import com.javid.sfp.util.AdvanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Digits;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * @author javid
 * Created on 6/1/2022
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final VerificationTokenService tokenService;
    private final ApplicationEventPublisher publisher;
    private final MessageSource messages;

    public CustomerRestController(CustomerService customerService, CustomerMapper customerMapper
            , VerificationTokenService tokenService, ApplicationEventPublisher publisher, MessageSource messages) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.tokenService = tokenService;
        this.publisher = publisher;
        this.messages = messages;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("customer")
    @ResponseBody
    public List<CustomerDto> getAll(Principal principal, Authentication authentication) {
        log.info("Principle Type: " + authentication.getName() + "\n" + principal.getName());
        return customerMapper.mapToDto(customerService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("customer/{id}")
    @ResponseBody
    public CustomerDto getById(@PathVariable(name = "id") String id) {
        return customerMapper.mapToDto(customerService.findByID(Long.valueOf(id)));
    }

    @PostMapping(value = "customer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> create(@Validated(AdvanceInfo.class) @ModelAttribute CustomerDto customerDto, HttpServletRequest request) {
        var customer = customerMapper.mapToEntity(customerDto);

        var user = customerService.create(customer);
        var local = request.getLocale();
        publisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), request.getContextPath()));
        var message = messages.getMessage("msg.reg.success", null, local);

        return Map.of("message", message, "redirect", "/index");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    @PutMapping(value = "customer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@Validated(AdvanceInfo.class) @ModelAttribute CustomerDto customerDto) {
        if (customerDto.getId() == null) {
            return;
        }

        customerService.update(customerMapper.mapToEntity(customerDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("customer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathParam("id") String id) {
        customerService.deleteByID(Long.valueOf(id));
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("customer/credit")
    public @ResponseBody
    Map<String, String> processBankFormPage(
            @Digits(integer = 15, fraction = 2)
            @RequestParam(name = "amount") BigDecimal amount,
            @RequestParam(name = "token") String token) {

        var verificationToken = tokenService.findByToken(token);
        if (tokenService.isExpiredById(verificationToken.getId())) {
            throw new BadRequestException("Payment time finished");
        }
        var user = verificationToken.getUser();
        var customer = customerService.findByID(user.getId());
        var credit = customer.getCredit();
        if (credit == null) {
            customer.setCredit(amount);
        } else {
            customer.setCredit(customer.getCredit().add(amount));
        }
        customerService.update(customer);
        tokenService.deleteById(verificationToken.getId());

        return Map.of("redirect", "/customer/profile");
    }
}
