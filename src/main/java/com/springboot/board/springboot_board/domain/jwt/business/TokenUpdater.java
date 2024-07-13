package com.springboot.board.springboot_board.domain.jwt.business;

import com.springboot.board.springboot_board.domain.jwt.domain.Token;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TokenUpdater {

    @Transactional
    public void tokenUpdate(Token token, String refreshToken) {
        token.updateRefreshToken(refreshToken);
    }
}
