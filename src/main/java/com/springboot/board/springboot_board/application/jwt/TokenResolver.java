package com.springboot.board.springboot_board.application.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TokenResolver {

    private static final String TOKEN_HEADER = "accessToken";
    private static final String TOKEN_TYPE = "Bearer";
    private static final int TOKEN_INDEX = 7;

    public String resolveToken(HttpServletRequest request) {
        String authorization = getHeader(request);

        if (isValidAuthorizationHeader(authorization)) {
            return authorization.substring(TOKEN_INDEX);
        }
        return null;
    }

    private String getHeader(HttpServletRequest request) {
        return request.getHeader(TOKEN_HEADER);
    }

    private boolean isValidAuthorizationHeader(String authorization) {
        return StringUtils.hasText(authorization) && authorization.startsWith(TOKEN_TYPE);
    }
}
