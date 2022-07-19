package com.isatoltar.trelloclone.board.data;

import org.springframework.stereotype.Component;

@Component
public class BoardDtoConverter {

    public BoardDto convertTo(Board board) {
        return new BoardDto(board.getId(), board.getName());
    }
}
