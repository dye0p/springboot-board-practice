package com.springboot.board.springboot_board.global.response;

import org.springframework.http.HttpStatus;

public class FailResponse<T> extends ApiResponse<T> {

    private FailResponse(int code, String message) {
        super(false, code, message);
    }

    public static <T> FailResponse<T> fail(HttpStatus status, String message) {
        return new FailResponse<>(status.value(), message);
    }
}
