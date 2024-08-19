package com.springboot.board.springboot_board.application.auth;

import com.springboot.board.springboot_board.application.auth.dto.MailSendRequest;
import com.springboot.board.springboot_board.application.auth.dto.MailVerifyRequest;
import com.springboot.board.springboot_board.application.member.MemberValidator;
import com.springboot.board.springboot_board.domain.opt.MailAuthCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthCodeVerifier authCodeVerifier;
    private final MemberValidator memberValidator;
    private final MailAuthCodeManager mailAuthCodeManager;

    public void checkAndSendEmail(final MailSendRequest mailSendRequest) {
        memberValidator.validateDuplicatedToEmail(mailSendRequest.email());
        mailAuthCodeManager.authCodeSaveAndSend(mailSendRequest.email(), MailAuthCode.of(mailSendRequest.email()));
    }

    public void verifyAuthCode(final MailVerifyRequest mailVerifyRequest) {
        authCodeVerifier.verifyAuthCode(mailVerifyRequest.email(), mailVerifyRequest.verifyCode());
    }
}
