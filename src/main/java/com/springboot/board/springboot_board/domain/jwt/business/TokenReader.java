package com.springboot.board.springboot_board.domain.jwt.business;

import com.springboot.board.springboot_board.domain.jwt.domain.Token;
import com.springboot.board.springboot_board.domain.jwt.repository.TokenRepository;
import com.springboot.board.springboot_board.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TokenReader {

    private final TokenRepository tokenRepository;

    @Transactional(readOnly = true)
    public Optional<Token> findToken(Member member) {
        return tokenRepository.findByMember(member);
    }
}
