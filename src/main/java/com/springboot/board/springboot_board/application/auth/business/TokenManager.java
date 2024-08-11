package com.springboot.board.springboot_board.application.auth.business;

import com.springboot.board.springboot_board.application.auth.dto.Tokens;
import com.springboot.board.springboot_board.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenManager {

    private final TokenProvider tokenProvider;

    public Tokens issueToken(Member member) {
        return tokenProvider.creatTokens(member.createTokenPayload());
    }
}
