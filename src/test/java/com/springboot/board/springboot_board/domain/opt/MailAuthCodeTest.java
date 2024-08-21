package com.springboot.board.springboot_board.domain.opt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MailAuthCodeTest {

    @DisplayName("MailAuthCode 생성시 RandomAuthCode의 길이는 8이다.")
    @Test
    void createMailAuthCodeWithRandomAuthCode() {
        //given //when
        MailAuthCode mailAuthCode = MailAuthCode.of("email");

        //then
        assertThat(mailAuthCode.getAuthCode()).hasSize(8);
    }

    @DisplayName("MailAuthCode 생성시 authState 상태는 false이다.")
    @Test
    void authStatusIsFalse() {
        //given //when
        MailAuthCode mailAuthCode = MailAuthCode.of("email");

        //then
        assertThat(mailAuthCode.isAuthState()).isFalse();
    }

    @DisplayName("MailAuthCode 생성시 authCode의 유효시간은 300초이다.")
    @Test
    void authCodeExpirationTime() {
        //given //when
        MailAuthCode mailAuthCode = MailAuthCode.of("email");

        //then
        assertThat(mailAuthCode.getExpirationTime()).isEqualTo(300L);
    }
}