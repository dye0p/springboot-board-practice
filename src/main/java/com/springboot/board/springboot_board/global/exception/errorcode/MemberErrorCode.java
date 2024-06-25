package com.springboot.board.springboot_board.global.exception.errorcode;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    EMAIL_DUPLICATION(HttpStatus.CONFLICT, "이미 사용중인 이메일 입니다."),
    LOGINID_DUPLICATION(HttpStatus.CONFLICT, "이미 사용중인 아이디 입니다."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 틀렸습니다.");

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
