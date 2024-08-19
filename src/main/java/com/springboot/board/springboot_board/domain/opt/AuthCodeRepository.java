package com.springboot.board.springboot_board.domain.opt;

import org.springframework.data.repository.CrudRepository;

public interface AuthCodeRepository extends CrudRepository<MailAuthCode, String> {

}
