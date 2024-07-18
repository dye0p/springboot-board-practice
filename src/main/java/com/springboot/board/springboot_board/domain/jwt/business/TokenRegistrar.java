package com.springboot.board.springboot_board.domain.jwt.business;

import com.springboot.board.springboot_board.domain.jwt.domain.Token;
import com.springboot.board.springboot_board.domain.jwt.repository.TokenRepository;
import com.springboot.board.springboot_board.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenRegistrar {

    private final TokenRepository tokenRepository;

    public void registrarToken(Member member, String refreshToken) {
        Token thisRefreshtoken = Token.of(member.getLoginId(), refreshToken);
        tokenRepository.save(thisRefreshtoken);
    }
}
