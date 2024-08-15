package com.springboot.board.springboot_board.infra.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailProvider {
    private static final String DEFAULT_TITLE = "회원가입을 위한 이메일 인증코드 입니다.";
    private static final String DEFAULT_MESSAGE = "이메일 인증 코드 입니다: ";

    private final JavaMailSender javaMailSender;

    @Async("mailExecutor")
    public void sendMail(String email, String authCode) {
        javaMailSender.send(createMailForm(email, authCode));
    }

    private SimpleMailMessage createMailForm(String email, String authCode) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(email);
        mailMessage.setSubject(DEFAULT_TITLE);
        mailMessage.setText(DEFAULT_MESSAGE + authCode);

        return mailMessage;
    }
}
