package com.springboot.board.springboot_board.domain.jwt.repository;

import com.springboot.board.springboot_board.domain.jwt.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
}
