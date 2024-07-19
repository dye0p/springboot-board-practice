package com.springboot.board.springboot_board.domain.jwt.business;

import com.springboot.board.springboot_board.domain.jwt.domain.BlackList;
import com.springboot.board.springboot_board.domain.jwt.repository.BlackListRepository;
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
