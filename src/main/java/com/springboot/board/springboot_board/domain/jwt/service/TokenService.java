package com.springboot.board.springboot_board.domain.jwt.service;

import com.springboot.board.springboot_board.domain.jwt.business.BlackListRegistrar;
import com.springboot.board.springboot_board.domain.jwt.business.TokenExpirationManager;
import com.springboot.board.springboot_board.domain.jwt.business.TokenManager;
import com.springboot.board.springboot_board.domain.jwt.business.TokenRegistrar;
import com.springboot.board.springboot_board.domain.jwt.dto.Tokens;
import com.springboot.board.springboot_board.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {
    private static final int MILLISECONDS_IN_SECOND = 1000;

    private final TokenManager tokenManager;
    private final TokenRegistrar tokenRegistrar;
    private final BlackListRegistrar blackListRegistrar;
    private final TokenExpirationManager tokenExpirationManager;

    public Tokens saveToken(Member member) {
        Tokens tokens = tokenManager.issueToken(member);
        tokenRegistrar.registrarToken(member, tokens.refreshToken());
        return tokens;
    }

    public void saveBlackList(String accessToken) {
        Long expriration = tokenExpirationManager.getExpriration(accessToken);
        blackListRegistrar.registrarBlackListToken(accessToken, expriration / MILLISECONDS_IN_SECOND);
    }
}
