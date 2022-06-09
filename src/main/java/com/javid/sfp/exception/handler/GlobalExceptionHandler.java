package com.javid.sfp.exception.handler;

import com.javid.sfp.exception.BadRequestException;
import com.javid.sfp.exception.ResourceNotFoundException;
import com.javid.sfp.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * @author javid
 * Created on 5/10/2022
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@ControllerAdvice(annotations = Controller.class, basePackages = "com.javid.sfp.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, Model model) {
        return errorForm(ex, model, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(BadRequestException ex, WebRequest request, Model model) {
        return errorForm(ex, model, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(UnauthorizedException ex, WebRequest request, Model model) {
        return errorForm(ex, model, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(MailException.class)
    public String handleMailException(RuntimeException ex, WebRequest request, Model model) {
        log.error(ex.getMessage());
        return errorForm(new RuntimeException("Sending email failed"), model, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        log.error("RuntimeException ", ex);
        return ResponseEntity.ok(null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        log.error("Exception ", ex);
        return ResponseEntity.ok(null);
    }

    private String errorForm(Exception ex, Model model, HttpStatus httpStatus) {
        log.warn(httpStatus + ": " + ex.getMessage());
        model.addAttribute("status", "Error " + httpStatus);
        return errorForm(ex, model);
    }

    private String errorForm(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error/form";
    }
}
