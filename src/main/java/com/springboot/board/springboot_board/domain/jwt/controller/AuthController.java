package com.springboot.board.springboot_board.domain.jwt.controller;

import com.springboot.board.springboot_board.domain.common.response.ApiResponse;
import com.springboot.board.springboot_board.domain.jwt.dto.Tokens;
import com.springboot.board.springboot_board.domain.jwt.service.TokenService;
import com.springboot.board.springboot_board.domain.member.dto.MemberLoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/v2/login")
    public ResponseEntity<ApiResponse<Tokens>> doLogin(@RequestBody MemberLoginRequest memberLoginRequest) {
        Tokens token = tokenService.login(memberLoginRequest);
        return ResponseEntity.ok(ApiResponse.success(token));
    }

    @GetMapping("/v1/logout")
    public ResponseEntity<ApiResponse<Void>> doLogout(HttpServletRequest request) {
        tokenService.logout(request);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
