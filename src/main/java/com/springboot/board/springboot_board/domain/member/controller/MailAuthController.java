package com.springboot.board.springboot_board.domain.member.controller;

import com.springboot.board.springboot_board.domain.member.dto.mail.MailSendRequest;
import com.springboot.board.springboot_board.domain.member.dto.mail.MailVerifyRequest;
import com.springboot.board.springboot_board.domain.member.service.MailAuthCodeService;
import com.springboot.board.springboot_board.domain.member.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/")
@RestController
public class MailAuthController {

    private final MailService mailService;
    private final MailAuthCodeService mailAuthCodeService;

    @PostMapping("/v1/auth/auth-code")
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid MailSendRequest mailSendRequest) {
        mailService.sendMail(mailSendRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/auth/auth-code")
    public ResponseEntity<Void> verifyEmail(@RequestBody @Valid MailVerifyRequest mailVerifyRequest) {
        mailAuthCodeService.verifyAuthCode(mailVerifyRequest);
        return ResponseEntity.ok().build();
    }
}