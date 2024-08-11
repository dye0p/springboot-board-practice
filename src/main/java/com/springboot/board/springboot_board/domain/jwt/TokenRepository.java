package com.springboot.board.springboot_board.domain.jwt;

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
}
