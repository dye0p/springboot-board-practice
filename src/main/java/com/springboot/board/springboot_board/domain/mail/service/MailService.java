package com.springboot.board.springboot_board.domain.mail.service;

import com.springboot.board.springboot_board.domain.mail.util.EmailSender;
import com.springboot.board.springboot_board.domain.mail.dto.MailSendRequest;
import com.springboot.board.springboot_board.domain.member.repository.MemberRepository;
import com.springboot.board.springboot_board.global.exception.custom.MailException;
import com.springboot.board.springboot_board.global.exception.errorcode.MailErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MailService {

    private final EmailSender emailSender;
    private final MemberRepository memberRepository;

    @Transactional
    public void checkAndSendEmail(MailSendRequest mailSendRequest) {
        if (memberRepository.existsByEmail(mailSendRequest.email())) {
            throw new MailException(MailErrorCode.EMAIL_DUPLICATED);
        }
        emailSender.sendMail(emailSender.createMailMessage(mailSendRequest.email()));
    }
}