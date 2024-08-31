package com.springboot.board.springboot_board.application.jwt;

import com.springboot.board.springboot_board.application.jwt.dto.Tokens;
import com.springboot.board.springboot_board.domain.member.Member;
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

    public Tokens saveToken(final Member member) {
        Tokens tokens = tokenManager.issueToken(member);
        tokenRegistrar.registrarToken(member, tokens.refreshToken());
        return tokens;
    }

    public void saveBlackList(final String accessToken) {
        Long expriration = tokenExpirationManager.getExpriration(accessToken);
        blackListRegistrar.registrarBlackListToken(accessToken, expriration / MILLISECONDS_IN_SECOND);
    }

}
