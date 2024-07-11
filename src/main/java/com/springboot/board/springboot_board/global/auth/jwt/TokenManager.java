package com.springboot.board.springboot_board.global.auth.jwt;

import com.springboot.board.springboot_board.domain.member.domain.Member;
import com.springboot.board.springboot_board.global.auth.jwt.dto.TokenPayload;
import com.springboot.board.springboot_board.global.auth.jwt.dto.Tokens;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenManager {

    private final TokenProvider tokenProvider;

    public Tokens issueToken(Member member) {
        return tokenProvider.creatTokens(new TokenPayload(member.getLoginId(), member.getRole().getValue()));
    }
}
