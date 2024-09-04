package com.springboot.board.springboot_board.application.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.springboot.board.springboot_board.IntegrationTestSupport;
import com.springboot.board.springboot_board.domain.opt.AuthCodeRepository;
import com.springboot.board.springboot_board.domain.opt.MailAuthCode;
import com.springboot.board.springboot_board.global.exception.custom.MailException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AuthCodeVerifierTest extends IntegrationTestSupport {

    @Autowired
    private AuthCodeRepository authCodeRepository;

    @Autowired
    private AuthCodeVerifier authCodeVerifier;

    @AfterEach
    void tearDown() {
        authCodeRepository.deleteAll();
    }

    @DisplayName("authCode가 일치한다면 redis에서 authCode를 삭제한다.")
    @Test
    void verifyAuthCodeDeleteAuthCodeInRedis() {
        //given
        String email = "test@test.com";
        String inputAuthCode = "1234";

        MailAuthCode mailAuthCode = MailAuthCode.builder()
                .email(email)
                .authCode("1234")
                .build();

        authCodeRepository.save(mailAuthCode);

        //when
        authCodeVerifier.verifyAuthCode(email, inputAuthCode);

        // then
        MailAuthCode result = authCodeRepository.findById(email).orElse(null);
        assertThat(result).isNull();
    }

    @DisplayName("authCode가 일치하지 않다면 예외를 던진다.")
    @Test
    void verifyAuthCodeThrownException() {
        //given
        String email = "test@test.com";
        String inputAuthCode = "1234";

        MailAuthCode mailAuthCode = MailAuthCode.builder()
                .email(email)
                .authCode("12345")
                .build();

        authCodeRepository.save(mailAuthCode);

        //when //then
        assertThatThrownBy(() -> authCodeVerifier.verifyAuthCode(email, inputAuthCode))
                .isInstanceOf(MailException.class)
                .hasMessage("인증 코드가 일치하지 않습니다.");
    }
}