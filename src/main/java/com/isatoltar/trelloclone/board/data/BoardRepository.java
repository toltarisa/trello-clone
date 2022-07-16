package com.isatoltar.trelloclone.board.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Optional<List<Board>> findAllByUserId(Integer userId);
}
