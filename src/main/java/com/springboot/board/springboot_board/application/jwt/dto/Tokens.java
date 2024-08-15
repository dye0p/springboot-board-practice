package com.springboot.board.springboot_board.application.jwt.dto;

public record Tokens(
        String accessToken,
        String refreshToken
) {

    public static Tokens of(String accessToken, String refreshToken) {
        return new Tokens(accessToken, refreshToken);
    }
}
