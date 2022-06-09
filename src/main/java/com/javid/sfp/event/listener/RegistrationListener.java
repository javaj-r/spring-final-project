package com.javid.sfp.event.listener;

import com.javid.sfp.event.OnRegistrationCompleteEvent;
import com.javid.sfp.model.VerificationToken;
import com.javid.sfp.model.base.User;
import com.javid.sfp.service.MailService;
import com.javid.sfp.service.VerificationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author javid
 * Created on 6/5/2022
 */
@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Value("${token.expiry.minutes}")
    private Integer expiryTimeInMinutes;

    private final VerificationTokenService tokenService;
    private final MessageSource messages;
    private final MailService mailService;

    public RegistrationListener(VerificationTokenService tokenService, MessageSource messages, MailService mailService) {
        this.tokenService = tokenService;
        this.messages = messages;
        this.mailService = mailService;
    }

    @Override
    public void onApplicationEvent(@NonNull OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        var savedToken = tokenService.saveOrUpdate(new VerificationToken(user, token), expiryTimeInMinutes);

        var confirmationUrl = event.getAppUrl() + "/confirm/register?token=" + token;
        var message = messages.getMessage("msg.reg.success", null, event.getLocale());
        var text = message
                + "\r\n"
                + "http://localhost:8080"
                + confirmationUrl
                + "\r\n"
                + "Expires on "
                + savedToken.getExpiryTime();

        mailService.sendSimpleMailMessage("Registration Confirmation", text, user.getEmail());
    }


}
