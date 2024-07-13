package com.springboot.board.springboot_board.domain.jwt.business;

import com.springboot.board.springboot_board.domain.jwt.domain.Token;
import com.springboot.board.springboot_board.domain.jwt.dto.SaveRefreshToken;
import com.springboot.board.springboot_board.domain.jwt.repository.TokenRepository;
import com.springboot.board.springboot_board.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class TokenRegistrar {

    private final TokenRepository tokenRepository;

    @Transactional
    public void registrarToken(Member member, String refreshToken) {
        SaveRefreshToken token = new SaveRefreshToken(member.getId(), refreshToken);
        Token thisRefreshtoken = Token.of(token.memberId(), refreshToken);
        tokenRepository.save(thisRefreshtoken);
    }
}
