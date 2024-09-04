package com.springboot.board.springboot_board.domain.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    boolean existsByLoginId(String loginId);

    Optional<Member> findByLoginId(String loginId);
}
