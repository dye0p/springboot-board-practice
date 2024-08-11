package com.springboot.board.springboot_board.global.response;

import org.springframework.http.HttpStatus;

public class SuccessResponse<T> extends ApiResponse<T> {

    private SuccessResponse(int code, String message, T data) {
        super(true, code, message, data);
    }

    private SuccessResponse(int code, String message) {
        super(true, code, message);
    }

    private SuccessResponse() {
        super(true, HttpStatus.OK.value(), "ok");
    }

    public static <T> SuccessResponse<T> of(HttpStatus status, T data) {
        return new SuccessResponse<>(status.value(), "ok", data);
    }

    public static <T> SuccessResponse<T> of(T data) {
        return new SuccessResponse<>(HttpStatus.OK.value(), "ok", data);
    }

    public static <T> SuccessResponse<T> ok(String message) {
        return new SuccessResponse<>(HttpStatus.OK.value(), message);
    }

    public static <T> SuccessResponse<T> ok() {
        return new SuccessResponse<>();
    }

}
