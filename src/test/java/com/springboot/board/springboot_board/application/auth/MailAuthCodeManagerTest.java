package com.springboot.board.springboot_board.application.auth;

import com.springboot.board.springboot_board.IntegrationTestSupport;
import com.springboot.board.springboot_board.domain.opt.AuthCodeRepository;
import com.springboot.board.springboot_board.domain.opt.MailAuthCode;
import com.springboot.board.springboot_board.domain.opt.MailMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MailAuthCodeManagerTest extends IntegrationTestSupport {

    @Autowired
    private MailAuthCodeManager mailAuthCodeManager;

    @Autowired
    private AuthCodeRepository authCodeRepository;

    @AfterEach
    void tearDown() {
        authCodeRepository.deleteAll();
    }

    @DisplayName("mailAuthCode를 redis에 저장하고 mail을 전송한다.")
    @Test
    void authCodeSaveAndSend() {
        //given
        MailAuthCode mailAuthCode = MailAuthCode.builder()
                .email("test@test.com")
                .authState(false)
                .authCode("1234")
                .expirationTime(60L)
                .build();

        //when
        mailAuthCodeManager.authCodeSaveAndSend(mailAuthCode.getEmail(), mailAuthCode);

        //then
        MailAuthCode result = authCodeRepository.findById(mailAuthCode.getEmail()).orElse(null);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(mailAuthCode.getEmail());
        verify(mailProvider, times(1)).sendMail(anyString(), any(MailMessage.class));
    }

}