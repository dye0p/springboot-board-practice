package com.springboot.board.springboot_board.domain.jwt.repository;

import com.springboot.board.springboot_board.domain.jwt.domain.BlackList;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList, String> {
}
