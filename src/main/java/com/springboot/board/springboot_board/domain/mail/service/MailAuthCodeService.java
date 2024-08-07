package com.springboot.board.springboot_board.domain.mail.service;

import com.springboot.board.springboot_board.domain.mail.dto.MailVerifyRequest;
import com.springboot.board.springboot_board.domain.mail.util.AuthCodeGenerator;
import com.springboot.board.springboot_board.global.exception.custom.MailException;
import com.springboot.board.springboot_board.global.exception.errorcode.MailErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MailAuthCodeService {

    private final AuthCodeGenerator authCodeGenerator;

    @Transactional(readOnly = true)
    public void verifyAuthCode(MailVerifyRequest mailVerifyRequest) {
        if (!authCodeGenerator.verifyAuthCode(mailVerifyRequest.email(), mailVerifyRequest.verifyCode())) {
            throw new MailException(MailErrorCode.INVALID_VERIFICATION_CODE);
        }
    }
}
