package com.springboot.board.springboot_board.domain.opt;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailMessage {

    private static final String DEFAULT_TITLE = "회원가입을 위한 이메일 인증코드 입니다.";
    private static final String DEFAULT_MESSAGE = "이메일 인증 코드 입니다: ";

    private String subject;
    private String message;
    private String authCode;

    @Builder
    private MailMessage(String subject, String message, String authCode) {
        this.subject = subject;
        this.message = message;
        this.authCode = authCode;
    }

    public static MailMessage of(String authCode) {
        return MailMessage.builder()
                .subject(DEFAULT_TITLE)
                .message(DEFAULT_MESSAGE)
                .authCode(authCode)
                .build();
    }
}
