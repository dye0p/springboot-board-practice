package com.springboot.board.springboot_board.global.exception.custom;

import com.springboot.board.springboot_board.global.exception.errorcode.ErrorCode;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(ErrorCode errorCode) {
        super(errorCode.message());
    }
}
