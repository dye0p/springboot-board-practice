package com.springboot.board.springboot_board.application.auth.dto;

public record TokenPayload(
        String memberId,
        String role
) {
}
