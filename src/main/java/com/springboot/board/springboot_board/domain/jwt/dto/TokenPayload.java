package com.springboot.board.springboot_board.domain.jwt.dto;

public record TokenPayload(
        String memberId,
        String role
) {
}
