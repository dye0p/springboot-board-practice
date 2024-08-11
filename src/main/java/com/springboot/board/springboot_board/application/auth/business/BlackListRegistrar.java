package com.springboot.board.springboot_board.application.auth.business;

import com.springboot.board.springboot_board.domain.jwt.BlackList;
import com.springboot.board.springboot_board.domain.jwt.BlackListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BlackListRegistrar {

    private final BlackListRepository blackListRepository;

    public void registrarBlackListToken(String accessToken, Long expiration) {
        BlackList blackList = BlackList.of(accessToken, expiration);
        blackListRepository.save(blackList);
    }
}
