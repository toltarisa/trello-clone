package com.isatoltar.trelloclone.board.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findAllByUserId(Integer userId);
}
