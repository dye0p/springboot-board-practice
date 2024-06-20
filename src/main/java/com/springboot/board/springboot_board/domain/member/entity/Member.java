package com.springboot.board.springboot_board.domain.member.entity;

import com.springboot.board.springboot_board.domain.Role;
import com.springboot.board.springboot_board.domain.common.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
