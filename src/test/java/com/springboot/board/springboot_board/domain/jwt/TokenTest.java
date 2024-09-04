package com.springboot.board.springboot_board.domain.jwt;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TokenTest {

    @DisplayName("24시간의 유효시간을 가진 Token객체를 생성한다.")
    @Test
    void ofRefreshTokenWith24HourExpirationTime() {
        //given
        final String loginId = "tsetId";
        final String refreshToken = "testRefreshToken";

        final Long is24Hour = 86400L;
        //when
        Token token = Token.of(loginId, refreshToken);

        //then
        assertThat(token.getRefreshExpirationTime()).isEqualTo(is24Hour);
    }
}