package com.springboot.board.springboot_board.domain.member.service;

import com.springboot.board.springboot_board.domain.member.dto.mail.MailSendRequest;
import com.springboot.board.springboot_board.domain.member.util.CreateMailForm;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final CreateMailForm createMailForm;

    @Transactional
    public void sendMail(MailSendRequest mailSendRequest) {
        SimpleMailMessage mailMessage = createMailForm.createMailMessaget(mailSendRequest.email());
        mailSender.send(mailMessage);
    }
}

