package com.springboot.board.springboot_board.application.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenExpirationManager {

    private final TokenProvider tokenProvider;

    public Long getExpriration(String accessToken) {
        return tokenProvider.getRemainingExpirationTime(accessToken);
    }
}
