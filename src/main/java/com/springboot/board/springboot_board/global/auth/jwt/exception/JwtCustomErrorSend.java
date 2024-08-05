package com.springboot.board.springboot_board.global.auth.jwt.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.board.springboot_board.global.exception.errorcode.TokenErrorCode;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtCustomErrorSend {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void handleException(HttpServletResponse response, TokenErrorCode errorCode, Object errorPoint) throws IOException {
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorPoint));
    }

}

