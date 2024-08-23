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

    @DisplayName("입력된 authCode가 현재 authCode와 같다면 true를 반환한다")
    @Test
    void isCorrectAuthCodeThenTrue() {
        //given
        MailAuthCode authCode = MailAuthCode.builder()
                .authCode("1234")
                .build();

        String inputAuthCode = "1234";

        //when
        boolean result = authCode.isCorrectAuthCode(inputAuthCode);

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("입력된 authCode가 현재 authCode와 다르다면 false를 반환한다")
    @Test
    void isCorrectAuthCodeThenFalse() {
        //given
        MailAuthCode authCode = MailAuthCode.builder()
                .authCode("12345")
                .build();

        String inputAuthCode = "1234";

        //when
        boolean result = authCode.isCorrectAuthCode(inputAuthCode);

        //then
        assertThat(result).isFalse();
    }
}