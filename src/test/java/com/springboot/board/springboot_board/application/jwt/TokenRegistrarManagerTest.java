package com.springboot.board.springboot_board.application.jwt;


import com.springboot.board.springboot_board.IntegrationTestSupport;
import com.springboot.board.springboot_board.domain.jwt.BlackList;
import com.springboot.board.springboot_board.domain.jwt.BlackListRepository;
import com.springboot.board.springboot_board.domain.jwt.Token;
import com.springboot.board.springboot_board.domain.jwt.TokenRepository;
import com.springboot.board.springboot_board.domain.member.Member;
import com.springboot.board.springboot_board.domain.member.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class TokenRegistrarManagerTest extends IntegrationTestSupport {

    @AfterEach
    void tearDown() {
        tokenRepository.deleteAll();
        blackListRepository.deleteAll();
    }

    @Autowired
    private TokenRegistrarManager tokenRegistrarManager;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private BlackListRepository blackListRepository;

    @DisplayName("refreshToken을 redis에 저장한다.")
    @Test
    void refreshRegistor() {
        //given
        Member member = Member.builder()
                .loginId("testId")
                .role(Role.USER)
                .build();

        String refreshToken = "testRefreshToken";

        //when
        tokenRegistrarManager.refreshRegistor(member, refreshToken);

        //then
        Token result = tokenRepository.findById("testId").orElse(null);
        assertThat(result)
                .extracting("loginId", "refreshToken")
                .containsExactly("testId", "testRefreshToken");
    }

    @DisplayName("blackListToken을 redis에 저장한다.")
    @Test
    void blackListResgistor() {
        //given
        String accessToken = "testAccessToken";
        Long expiration = 1000L;

        //when
        tokenRegistrarManager.blackListResgistor(accessToken, expiration);

        //then
        BlackList result = blackListRepository.findById(accessToken).orElse(null);
        assertThat(result)
                .extracting("accessToken", "expirationTime")
                .containsExactly("testAccessToken", 1000L);
    }
}