package com.springboot.board.springboot_board.domain.member.controller;

import com.springboot.board.springboot_board.domain.common.response.ApiResponse;
import com.springboot.board.springboot_board.domain.jwt.dto.Tokens;
import com.springboot.board.springboot_board.domain.member.dto.MemberLoginRequest;
import com.springboot.board.springboot_board.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/v2/login")
    public ResponseEntity<ApiResponse<Tokens>> doLogin(@RequestBody MemberLoginRequest memberLoginRequest) {
        Tokens token = memberService.login(memberLoginRequest);
        return ResponseEntity.ok(ApiResponse.success(token));
    }
}
