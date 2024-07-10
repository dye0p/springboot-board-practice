package com.springboot.board.springboot_board.domain.member.controller;

import com.springboot.board.springboot_board.domain.member.dto.MemberLoginRequest;
import com.springboot.board.springboot_board.domain.member.service.MemberService;
import com.springboot.board.springboot_board.global.auth.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/v1/login")
    public ResponseEntity<JwtResponse> dologin(@RequestBody MemberLoginRequest memberLoginRequest) {
        JwtResponse token = memberService.login(memberLoginRequest);
        return ResponseEntity.ok(token);
    }
}
