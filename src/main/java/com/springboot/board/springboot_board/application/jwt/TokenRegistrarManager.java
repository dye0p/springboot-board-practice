package com.springboot.board.springboot_board.application.jwt;

import com.springboot.board.springboot_board.domain.jwt.BlackList;
import com.springboot.board.springboot_board.domain.jwt.BlackListRepository;
import com.springboot.board.springboot_board.domain.jwt.Token;
import com.springboot.board.springboot_board.domain.jwt.TokenRepository;
import com.springboot.board.springboot_board.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenRegistrarManager implements TokenRegistrar {

    private final BlackListRepository blackListRepository;
    private final TokenRepository tokenRepository;

    @Override
    public void blackListResgistor(String accessToken, Long expiration) {
        BlackList blackList = BlackList.of(accessToken, expiration);
        blackListRepository.save(blackList);
    }

    @Override
    public void refreshRegistor(Member member, String refreshToken) {
        Token thisRefreshtoken = Token.of(member.getLoginId(), refreshToken);
        tokenRepository.save(thisRefreshtoken);
    }
}
