package com.springboot.board.springboot_board.application.jwt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.springboot.board.springboot_board.application.jwt.dto.TokenPayload;
import com.springboot.board.springboot_board.application.jwt.dto.Tokens;
import com.springboot.board.springboot_board.global.exception.custom.JwtAuthenticationException;
import com.springboot.board.springboot_board.global.properties.JwtProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TokenProviderTest {

    @DisplayName("accessToken과 refreshToken을 발행한다.")
    @Test
    void creatTokens() {
        //given
        TokenProvider tokenProvider = createTokenProvier();
        TokenPayload tokenPayload = TokenPayload.of("testId", "testRole");

        //when
        Tokens tokens = tokenProvider.creatTokens(tokenPayload);

        //then
        assertThat(tokens.accessToken()).isNotNull();
        assertThat(tokens.refreshToken()).isNotNull();
        assertThat(tokens.accessToken()).isNotEqualTo(tokens.refreshToken());
    }

    @DisplayName("token으로 loginId를 추출할 수 있다.")
    @Test
    void getLogidId() {
        //given
        TokenProvider tokenProvider = createTokenProvier();
        TokenPayload tokenPayload = TokenPayload.of("testId", "testRole");

        Tokens tokens = tokenProvider.creatTokens(tokenPayload);

        //when
        String logidId = tokenProvider.getLogidId(tokens.accessToken());

        //then
        assertThat(logidId).isEqualTo("testId");
    }

    @DisplayName("token으로 role을 추출할 수 있다.")
    @Test
    void getRole() {
        //given
        TokenProvider tokenProvider = createTokenProvier();
        TokenPayload tokenPayload = TokenPayload.of("testId", "testRole");

        Tokens tokens = tokenProvider.creatTokens(tokenPayload);

        //when
        String logidId = tokenProvider.getRole(tokens.accessToken());

        //then
        assertThat(logidId).isEqualTo("testRole");
    }

    @DisplayName("token의 남은 유효시간을 추출할 수 있다.")
    @Test
    void getRemainingExpirationTime() {
        //given
        TokenProvider tokenProvider = createTokenProvier();
        TokenPayload tokenPayload = TokenPayload.of("testId", "testRole");

        Tokens tokens = tokenProvider.creatTokens(tokenPayload);

        //when
        long remainingExpirationTime = tokenProvider.getRemainingExpirationTime(tokens.accessToken());

        //then
        assertThat(remainingExpirationTime).isGreaterThan(0);
    }

    @DisplayName("유효하지 않은 token을 검증하면 예외를 던진다.")
    @Test
    void validateToken() {
        //given
        TokenProvider tokenProvider = createTokenProvier();
        String token = "invalToken";

        //when //then
        assertThatThrownBy(() -> tokenProvider.validateToken(token))
                .isInstanceOf(JwtAuthenticationException.class)
                .hasMessage("유효하지 않은 인증 토큰입니다.");

    }

    @DisplayName("만료된 token을 검증하면 예외를 던진다.")
    @Test
    void validateTokenForExpirednToken() throws InterruptedException {
        //given
        JwtProperties jwtProperties = new JwtProperties(
                "testSecretKey12345testSecretKey12345testSecretKey12345",
                1000L,
                0L);

        TokenProvider tokenProvider = new TokenProvider(jwtProperties);
        TokenPayload tokenPayload = TokenPayload.of("testId", "testRole");

        Tokens tokens = tokenProvider.creatTokens(tokenPayload);

        //when //then
        Thread.sleep(1000);

        assertThatThrownBy(() -> tokenProvider.validateToken(tokens.accessToken()))
                .isInstanceOf(JwtAuthenticationException.class)
                .hasMessage("토큰의 유효기간이 만료되었습니다.");
    }

    @DisplayName("비어있는 token을 검증하면 예외를 던진다.")
    @Test
    void validateTokenForIllegalArgumentException() {
        //given
        TokenProvider tokenProvider = createTokenProvier();
        String token = " ";

        //when //then
        assertThatThrownBy(() -> tokenProvider.validateToken(token))
                .isInstanceOf(JwtAuthenticationException.class)
                .hasMessage("토큰 정보가 null입니다.");
    }

    private TokenProvider createTokenProvier() {
        JwtProperties jwtProperties = createJwtProperties();
        return new TokenProvider(jwtProperties);
    }

    private JwtProperties createJwtProperties() {
        String secretKey = "testSecretKey12345testSecretKey12345testSecretKey12345";
        Long accessExpirationTime = 100000L;
        Long refreshExpirationTime = 100000L;

        return new JwtProperties(
                secretKey, accessExpirationTime, refreshExpirationTime);
    }
}