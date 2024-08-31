package com.springboot.board.springboot_board.presentation.auth;

import com.springboot.board.springboot_board.application.auth.AuthService;
import com.springboot.board.springboot_board.application.auth.dto.MailSendRequest;
import com.springboot.board.springboot_board.application.auth.dto.MailVerifyRequest;
import com.springboot.board.springboot_board.application.jwt.dto.Tokens;
import com.springboot.board.springboot_board.application.member.dto.request.MemberLoginRequest;
import com.springboot.board.springboot_board.global.response.ApiResponse;
import com.springboot.board.springboot_board.global.response.SuccessResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/v2/auth/auth-code")
    public ResponseEntity<ApiResponse<Void>> sendEmail(@RequestBody @Valid MailSendRequest mailSendRequest) {
        authService.checkAndSendEmail(mailSendRequest);
        return ResponseEntity.ok(SuccessResponse.ok("인증코드가 전송되었습니다"));
    }

    @GetMapping("/v2/auth/auth-code")
    public ResponseEntity<ApiResponse<Void>> verifyMailAuthCode(@RequestBody @Valid MailVerifyRequest mailVerifyRequest) {
        authService.verifyAuthCode(mailVerifyRequest);
        return ResponseEntity.ok(SuccessResponse.ok("인증되었습니다"));
    }

    @PostMapping("/v2/login")
    public ResponseEntity<ApiResponse<Tokens>> doLogin(@RequestBody @Valid MemberLoginRequest memberLoginRequest) {
        Tokens token = authService.login(memberLoginRequest);
        return ResponseEntity.ok(SuccessResponse.of(token));
    }

    @GetMapping("/v1/logout")
    public ResponseEntity<ApiResponse<Void>> doLogout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok(SuccessResponse.ok());
    }
}