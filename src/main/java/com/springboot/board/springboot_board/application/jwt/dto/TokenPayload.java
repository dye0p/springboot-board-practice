package com.springboot.board.springboot_board.application.jwt.dto;

import lombok.Builder;

@Builder
public record TokenPayload(
        String memberId,
        String role
) {

    public static TokenPayload of(String memberId, String role) {
        return TokenPayload.builder()
                .memberId(memberId)
                .role(role)
                .build();
    }

}
