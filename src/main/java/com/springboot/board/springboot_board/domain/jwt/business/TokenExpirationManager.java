package com.springboot.board.springboot_board.domain.jwt.business;

import com.springboot.board.springboot_board.global.auth.jwt.TokenProvider;
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
