package com.springboot.board.springboot_board.global.auth.jwt.filter;

import com.springboot.board.springboot_board.domain.common.response.ApiResponse;
import com.springboot.board.springboot_board.domain.common.response.FailResponse;
import com.springboot.board.springboot_board.global.auth.jwt.exception.JwtCustomErrorSend;
import com.springboot.board.springboot_board.global.exception.custom.JwtAuthenticationException;
import com.springboot.board.springboot_board.global.exception.errorcode.TokenErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (JwtAuthenticationException exception) {
            TokenErrorCode errorCode = TokenErrorCode.fromMessage(exception.getMessage());
            ApiResponse<Object> apiResponse = FailResponse.fail(errorCode.getHttpStatus(),exception.getMessage());

            JwtCustomErrorSend.handleException(response, errorCode, apiResponse);
        }
    }
}
