package com.springboot.board.springboot_board.application.jwt;

import com.springboot.board.springboot_board.application.jwt.dto.TokenPayload;
import com.springboot.board.springboot_board.application.jwt.dto.Tokens;
import com.springboot.board.springboot_board.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenCreator {

    private final TokenProvider tokenProvider;

    public Tokens issueToken(Member member) {
        return tokenProvider.creatTokens(createTokenPayload(member));
    }

    private TokenPayload createTokenPayload(Member member) {
        return TokenPayload.of(member.getLoginId(), member.getRole().getValue());
    }
}
