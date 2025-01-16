package com.dev.quanlyhocsinh.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailService {

    JavaMailSender emailSender;
    public void senSimpleMessage(String to, String subject, String text ){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setTo(subject);
        message.setTo(text);
        emailSender.send(message);
    }
}
