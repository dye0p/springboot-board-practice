package com.springboot.board.springboot_board.application.auth;

import com.springboot.board.springboot_board.application.auth.dto.MailSendRequest;
import com.springboot.board.springboot_board.application.auth.dto.MailVerifyRequest;
import com.springboot.board.springboot_board.application.jwt.dto.Tokens;
import com.springboot.board.springboot_board.application.member.LoginManager;
import com.springboot.board.springboot_board.application.member.MemberValidator;
import com.springboot.board.springboot_board.application.member.dto.request.MemberLoginRequest;
import com.springboot.board.springboot_board.domain.opt.MailAuthCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthCodeVerifier authCodeVerifier;
    private final MemberValidator memberValidator;
    private final MailAuthCodeManager mailAuthCodeManager;
    private final LoginManager loginManager;

    public Tokens login(final MemberLoginRequest memberLoginRequest) {
        return loginManager.login(memberLoginRequest);
    }

    public void logout(final HttpServletRequest request) {
        loginManager.logout(request);
    }

    public void checkAndSendEmail(final MailSendRequest mailSendRequest) {
        memberValidator.validateDuplicatedToEmail(mailSendRequest.email());
        mailAuthCodeManager.authCodeSaveAndSend(mailSendRequest.email(), MailAuthCode.of(mailSendRequest.email()));
    }

    public void verifyAuthCode(final MailVerifyRequest mailVerifyRequest) {
        authCodeVerifier.verifyAuthCode(mailVerifyRequest.email(), mailVerifyRequest.verifyCode());
    }

}
