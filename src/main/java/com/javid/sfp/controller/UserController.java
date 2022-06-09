package com.javid.sfp.controller;

import com.javid.sfp.model.VerificationToken;
import com.javid.sfp.service.MailService;
import com.javid.sfp.service.UserService;
import com.javid.sfp.service.VerificationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * @author javid
 * Created on 6/5/2022
 */
@Slf4j
@Controller
public class UserController {

    @Value("${token.expiry.minutes}")
    private Integer expiryTimeInMinutes;

    private final UserService userService;
    private final VerificationTokenService tokenService;
    private final MailService mailService;
    private final MessageSource messages;

    public UserController(UserService userService, VerificationTokenService tokenService
            , MailService mailService, MessageSource messages) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.mailService = mailService;
        this.messages = messages;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/confirm/register")
    public String confirmRegistration(@RequestParam("token") String token, Model model) {
        var verificationToken = tokenService.findByToken(token);

        if (tokenService.isExpiredById(verificationToken.getId())) {
            model.addAttribute("status", "Error " + HttpStatus.UNAUTHORIZED);
            model.addAttribute("message", "Your verification token expired on: " +
                    verificationToken.getExpiryTime().toString());

            model.addAttribute("method", "GET");
            model.addAttribute("action", "/user/token/resend");
            model.addAttribute("submitTitle", "Send new token");
            model.addAttribute("hiddenInputs", Map.of(
                    "token", token
            ));

            return "error/form";
        }

        verificationToken.getUser().setEnabled(true);
        userService.update(verificationToken.getUser());
        tokenService.deleteById(verificationToken.getId());

        return "redirect:login";
    }

    @GetMapping("/user/token/resend")
    public String resendRegistrationToken(@RequestParam("token") String existingToken, HttpServletRequest request) {
        var verificationToken = tokenService.findByToken(existingToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken = tokenService.saveOrUpdate(verificationToken, expiryTimeInMinutes);

        String appUrl = "http://" + request.getServerName() +
                        ":" + request.getServerPort() +
                        request.getContextPath();

        sendNewVerificationTokenEmail(appUrl, request.getLocale(), verificationToken);

        return "redirect: /index";
    }

    private void sendNewVerificationTokenEmail(String contextPath, Locale locale, VerificationToken newToken) {
        String confirmationUrl = contextPath + "/confirm/register?token=" + newToken.getToken();
        String message = messages.getMessage("msg.token.resend", null, locale);
        var text = message
                + "\r\n"
                + confirmationUrl
                + "\r\n"
                + "Expires on "
                + newToken.getExpiryTime();

        mailService.sendSimpleMailMessage("Resend Registration Token", text, newToken.getUser().getEmail());
    }

}
