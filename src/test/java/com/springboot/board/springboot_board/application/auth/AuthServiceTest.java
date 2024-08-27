package com.springboot.board.springboot_board.application.auth;

import com.springboot.board.springboot_board.IntegrationTestSupport;
import com.springboot.board.springboot_board.application.auth.dto.MailSendRequest;
import com.springboot.board.springboot_board.application.auth.dto.MailVerifyRequest;
import com.springboot.board.springboot_board.application.member.MemberValidator;
import com.springboot.board.springboot_board.domain.opt.MailAuthCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

class AuthServiceTest extends IntegrationTestSupport {

    @InjectMocks
    private AuthService authService;

    @Mock
    private MemberValidator memberValidator;

    @Mock
    private MailAuthCodeManager mailAuthCodeManager;

    @Mock
    private AuthCodeVerifier authCodeVerifier;

    @DisplayName("이메일 중복을 확인하고 메일을 저장하고 전송한다.")
    @Test
    void checkAndSendEmail() {
        //given
        MailSendRequest mailSendRequest = new MailSendRequest("test@test.com");

        //when
        authService.checkAndSendEmail(mailSendRequest);

        //then
        verify(memberValidator).validateDuplicatedToEmail(mailSendRequest.email());
        verify(mailAuthCodeManager).authCodeSaveAndSend(eq(mailSendRequest.email()), any(MailAuthCode.class));
    }

    @DisplayName("인증코드를 검증 한다.")
    @Test
    void verifyAuthCode() {
        //given
        MailVerifyRequest mailVerifyRequest = new MailVerifyRequest("1234", "test@test.com");

        //when
        authService.verifyAuthCode(mailVerifyRequest);

        //then
        verify(authCodeVerifier).verifyAuthCode(eq(mailVerifyRequest.email()), anyString());
    }
}