package com.springboot.board.springboot_board.domain.member.service;

import com.springboot.board.springboot_board.domain.member.util.AuthCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailSender {
    private static final String DEFAULT_TITLE = "회원가입을 위한 이메일 인증코드 입니다.";
    private static final String DEFAULT_MESSAGE = "이메일 인증 코드 입니다: ";

    private final AuthCodeGenerator authCodeGenerator;
    private final JavaMailSender javaMailSender;

    @Async("mailExecutor")
    public void sendMail(SimpleMailMessage message) {
        javaMailSender.send(message);
    }

    public SimpleMailMessage createMailMessage(String toEmail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String authCode = generateAndSaveAuthCode(toEmail);

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(DEFAULT_TITLE);
        mailMessage.setText(DEFAULT_MESSAGE + authCode);

        return mailMessage;
    }

    private String generateAndSaveAuthCode(String toemail) {
        String authCode = authCodeGenerator.generateAuthCode();
        authCodeGenerator.saveAuthCode(toemail, authCode);
        return authCode;
    }
}

