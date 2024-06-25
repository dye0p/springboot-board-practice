package com.springboot.board.springboot_board.domain.common;

import lombok.Getter;

@Getter
public enum Role {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
