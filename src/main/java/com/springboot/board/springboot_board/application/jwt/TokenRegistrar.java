package com.springboot.board.springboot_board.application.jwt;

import com.springboot.board.springboot_board.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
public interface TokenRegistrar {
    void blackListResgistor(String accessToken, Long expiration);
    void refreshRegistor(Member member, String refreshToken);
}
