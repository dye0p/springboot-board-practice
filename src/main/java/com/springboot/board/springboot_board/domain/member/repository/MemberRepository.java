package com.springboot.board.springboot_board.domain.member.repository;

import com.springboot.board.springboot_board.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    boolean existsByLoginId(String loginId);
}
