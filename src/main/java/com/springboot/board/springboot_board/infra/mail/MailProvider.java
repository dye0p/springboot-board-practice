package com.springboot.board.springboot_board.infra.mail;

import com.springboot.board.springboot_board.domain.opt.MailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailProvider {

    private final JavaMailSender javaMailSender;

    @Async("mailExecutor")
    public void sendMail(String toEmail, MailMessage toMailMessage) {
        javaMailSender.send(createMailForm(toEmail, toMailMessage));
    }

    private SimpleMailMessage createMailForm(String toEmail, MailMessage toMailMessage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(toMailMessage.getSubject());
        mailMessage.setText(toMailMessage.getMessage() + toMailMessage.getAuthCode());

        return mailMessage;
    }
}
