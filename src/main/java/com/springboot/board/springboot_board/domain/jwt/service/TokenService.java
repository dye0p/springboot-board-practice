package com.springboot.board.springboot_board.domain.jwt.service;

import com.springboot.board.springboot_board.domain.jwt.business.TokenRegistrar;
import com.springboot.board.springboot_board.domain.jwt.business.TokenManager;
import com.springboot.board.springboot_board.domain.jwt.business.TokenReader;
import com.springboot.board.springboot_board.domain.jwt.business.TokenUpdater;
import com.springboot.board.springboot_board.domain.jwt.domain.Token;
import com.springboot.board.springboot_board.domain.jwt.dto.Tokens;
import com.springboot.board.springboot_board.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenManager tokenManager;
    private final TokenRegistrar tokenRegistrar;
    private final TokenReader tokenReader;
    private final TokenUpdater tokenUpdater;

    public Tokens registrarOrUpdateToken(Member member) {
        Tokens tokens = tokenManager.issueToken(member);

        tokenReader.findToken(member)
                .ifPresentOrElse(
                        (Token token) -> tokenUpdater.tokenUpdate(token, tokens.refreshToken()),
                        () -> tokenRegistrar.registrarToken(member, tokens.refreshToken()));

        return tokens;
    }
}
