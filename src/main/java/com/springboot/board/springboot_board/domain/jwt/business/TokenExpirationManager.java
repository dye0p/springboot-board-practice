package com.springboot.board.springboot_board.domain.jwt.business;

import com.springboot.board.springboot_board.global.auth.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenExpirationManager {

    private final TokenProvider tokenProvider;

    public Long getExpriration(String accessToken) {
        Date expiryDate = tokenProvider.getExpiryDate(accessToken);
        return expiryDate.getTime();
    }
}
