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
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author javid
 * Created on 5/10/2022
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(annotations = RestController.class, basePackages = "com.javid.sfp.controller.api")
public class GlobalRestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class, ConstraintViolationException.class})
    public Map<String, String> handleBindExceptions(BindException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    String fieldName = error.getField();
                    String errorMessage = error.getDefaultMessage();
                    if (errorMessage != null) {
                        errorMessage = errorMessage.split(",")[0];
                    }
                    errors.put(fieldName, errorMessage);
                });
        return errors;
    }

    @ExceptionHandler({ResourceNotFoundException.class, BadRequestException.class, UnauthorizedException.class})
    public Map<String, String> handleException(RuntimeException ex, WebRequest request) {
        return Map.of(
                "message", ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(MailException.class)
    public Map<String, String> handleMailException(RuntimeException ex,  WebRequest request) {
        log.warn("Sending email exception", ex);
        return Map.of(
                "error", "Bad request",
                "message", "Sending email failed"
        );
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(Exception ex, WebRequest request) {
        log.error("RuntimeException ", ex);
        return ResponseEntity.ok(null);
    }
}
