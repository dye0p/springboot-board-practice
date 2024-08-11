package com.springboot.board.springboot_board.application.auth.business;

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
