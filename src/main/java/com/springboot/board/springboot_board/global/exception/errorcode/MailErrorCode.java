package com.springboot.board.springboot_board.global.exception.errorcode;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MailErrorCode implements ErrorCode {

    EMAIL_DUPLICATED(HttpStatus.CONFLICT, "해당 이메일은 이미 가입된 이메일 주소입니다."),
    INVALID_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, "인증 코드가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public String message() {
        return message;
    }
}
