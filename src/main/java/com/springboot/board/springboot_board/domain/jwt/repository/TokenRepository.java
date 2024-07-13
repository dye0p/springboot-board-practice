package com.springboot.board.springboot_board.domain.jwt.repository;

import com.springboot.board.springboot_board.domain.jwt.domain.Token;
import com.springboot.board.springboot_board.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    boolean existsByRefreshToken(String token);

    Optional<Token> findByMember(Member member);
}
