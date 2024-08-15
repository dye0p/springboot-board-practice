package com.springboot.board.springboot_board.application.jwt.dto;

public record TokenPayload(
        String memberId,
        String role
) {
}
