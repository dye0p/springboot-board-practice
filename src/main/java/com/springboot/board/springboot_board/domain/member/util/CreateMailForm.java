package com.springboot.board.springboot_board.domain.member.util;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateMailForm {

    private static final String DEFAULT_TITLE = "회원가입을 위한 이메일 인증코드 입니다.";
    private static final String DEFAULT_MESSAGE = "이메일 인증 코드 입니다: ";
    private final AuthCodeGenerator authCodeGenerator;

    public SimpleMailMessage createMailMessaget(String toEmail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String authCode = authCodeGenerator.generateAuthCode();
        authCodeGenerator.saveAuthCode(toEmail, authCode);

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(DEFAULT_TITLE);
        mailMessage.setText(DEFAULT_MESSAGE + authCode);

        return mailMessage;
    }
}

