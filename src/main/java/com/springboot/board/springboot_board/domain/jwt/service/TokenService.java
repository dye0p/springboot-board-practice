package com.springboot.board.springboot_board.domain.jwt.service;

import com.springboot.board.springboot_board.domain.jwt.business.TokenManager;
import com.springboot.board.springboot_board.domain.jwt.business.TokenRegistrar;
import com.springboot.board.springboot_board.domain.jwt.dto.Tokens;
import com.springboot.board.springboot_board.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenManager tokenManager;
    private final TokenRegistrar tokenRegistrar;

    public Tokens saveToken(Member member) {
        Tokens tokens = tokenManager.issueToken(member);
        tokenRegistrar.registrarToken(member, tokens.refreshToken());
        return tokens;
    }
}
