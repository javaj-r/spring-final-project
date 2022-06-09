package com.javid.sfp.service.impl;

import com.javid.sfp.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author javid
 * Created on 6/7/2022
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendSimpleMailMessage(String subject, String text, String... to) {
        var email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(text);
        email.setTo(to);

        log.info(text);

        mailSender.send(email);
    }
}
