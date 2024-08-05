package com.springboot.board.springboot_board.global.exception.errorcode;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode {

    NOT_FOUND_TOKEN(HttpStatus.NOT_FOUND, "토큰을 찾을 수 없습니다"),
    AUTH_MUST_AUTHORIZED_URI(HttpStatus.UNAUTHORIZED, "해당 uri는 권한 인증이 필수입니다. 만료된 토큰이거나 인증 정보가 없습니다."),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 인증 토큰입니다."),
    AUTH_TOKEN_HAS_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰의 유효기간이 만료되었습니다."),
    AUTH_TOKEN_IS_UNSUPPORTED(HttpStatus.UNAUTHORIZED, "토큰 형식이 jwt와는 다른 형식입니다."),
    AUTH_IS_NULL(HttpStatus.BAD_REQUEST, "토큰 정보가 null입니다.");

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

    public static TokenErrorCode fromMessage(String message) {
        return Arrays.stream(values())
                .filter(tokenErrorCode -> tokenErrorCode.getMessage().equals(message))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 메세지에 일치하는 ErrorCode가 없습니다 " + message));
    }
}
