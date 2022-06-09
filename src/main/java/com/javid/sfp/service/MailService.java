package com.javid.sfp.service;

/**
 * @author javid
 * Created on 6/7/2022
 */
public interface MailService {

    void sendSimpleMailMessage(String subject, String text, String... to);
}
