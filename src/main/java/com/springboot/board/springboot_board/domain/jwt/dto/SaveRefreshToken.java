package com.springboot.board.springboot_board.domain.jwt.dto;

public record SaveRefreshToken(
        Long memberId,
        String refreshToken
) {
}

