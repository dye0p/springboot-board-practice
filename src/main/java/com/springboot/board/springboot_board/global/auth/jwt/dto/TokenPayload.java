package com.springboot.board.springboot_board.global.auth.jwt.dto;

public record TokenPayload(
        String memberId,
        String role
) {
}
