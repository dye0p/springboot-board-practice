package com.springboot.board.springboot_board.domain.opt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MailMessageTest {

    @DisplayName("이메일 인증 형식을 생성한다")
    @Test
    void createMailMessage() {
        //given  //when
        MailMessage mailMessage = MailMessage.builder()
                .subject("테스트 이메일 인증 제목 입니다.")
                .message("테스트 이메일 인증 내용 입니다.")
                .authCode("12345")
                .build();

        //then
        assertThat(mailMessage)
                .extracting("subject", "message", "authCode")
                .containsExactly(
                        mailMessage.getSubject(),
                        mailMessage.getMessage(),
                        mailMessage.getAuthCode());
    }

    @DisplayName("authCode로 지정된 subject의 이메일을 생성한다.")
    @Test
    void ofSubject() {
        //given
        final String authCode = "12345";

        //when
        MailMessage mailMessage = MailMessage.of(authCode);

        //then
        assertThat(mailMessage.getSubject()).isEqualTo("회원가입을 위한 이메일 인증코드 입니다.");
    }

    @DisplayName("authCode로 지정된 message의 이메일을 생성한다.")
    @Test
    void ofMessage() {
        //given
        final String authCode = "12345";

        //when
        MailMessage mailMessage = MailMessage.of(authCode);

        //then
        assertThat(mailMessage.getMessage()).isEqualTo("이메일 인증 코드 입니다: ");
    }
}