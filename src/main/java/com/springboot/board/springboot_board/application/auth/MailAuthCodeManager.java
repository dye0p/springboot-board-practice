package com.springboot.board.springboot_board.application.auth;

import com.springboot.board.springboot_board.domain.opt.AuthCodeRepository;
import com.springboot.board.springboot_board.domain.opt.MailAuthCode;
import com.springboot.board.springboot_board.domain.opt.MailMessage;
import com.springboot.board.springboot_board.infra.mail.MailProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailAuthCodeManager {

    private final AuthCodeRepository authCodeRepository;
    private final MailProvider mailProvider;

    public void authCodeSaveAndSend(String email, MailAuthCode mailAuthCode) {
        saveAuthCode(mailAuthCode);
        snedMail(email, mailAuthCode);
    }

    private void snedMail(String email, MailAuthCode mailAuthCode) {
        mailProvider.sendMail(email, MailMessage.of(mailAuthCode.getAuthCode()));
    }

    private void saveAuthCode(MailAuthCode mailAuthCode) {
        authCodeRepository.save(mailAuthCode);
    }
}
