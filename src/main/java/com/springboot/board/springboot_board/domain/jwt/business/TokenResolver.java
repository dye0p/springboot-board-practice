package com.springboot.board.springboot_board.domain.jwt.business;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TokenResolver {

    private static final String TOKEN_HEADER = "accessToken";
    private static final String TOKEN_TYPE = "Bearer";
    private static final int TOKEN_INDEX = 7;

    public String resolveToken(HttpServletRequest request) {
        String authorization = request.getHeader(TOKEN_HEADER);

        if (StringUtils.hasText(authorization) && authorization.startsWith(TOKEN_TYPE)) {
            return authorization.substring(TOKEN_INDEX);
        }
        return null;
    }
}
