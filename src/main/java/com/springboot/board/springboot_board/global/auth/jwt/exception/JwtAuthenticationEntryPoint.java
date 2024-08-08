package com.springboot.board.springboot_board.global.auth.jwt.exception;

import com.springboot.board.springboot_board.domain.common.response.ApiResponse;
import com.springboot.board.springboot_board.domain.common.response.FailResponse;
import com.springboot.board.springboot_board.global.exception.errorcode.TokenErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        TokenErrorCode tokenErrorCode = TokenErrorCode.AUTH_MUST_AUTHORIZED_URI;
        ApiResponse<Object> apiResponse = FailResponse.fail(tokenErrorCode.getHttpStatus(),tokenErrorCode.getMessage());

        log.warn(tokenErrorCode.getMessage(), authException);
        JwtCustomErrorSend.handleException(response, tokenErrorCode, apiResponse);
    }
}
