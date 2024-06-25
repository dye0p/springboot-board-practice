package com.springboot.board.springboot_board.global.exception.errorcode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus httpStatus();
    String message();

}
