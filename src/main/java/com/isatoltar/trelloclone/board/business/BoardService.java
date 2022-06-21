package com.isatoltar.trelloclone.board.business;

import com.isatoltar.trelloclone.board.data.Board;
import com.isatoltar.trelloclone.board.data.BoardRepository;
import com.isatoltar.trelloclone.board.data.CreateBoardRequest;
import com.isatoltar.trelloclone.shared.exception.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoardService {

    final BoardRepository boardRepository;

    public void createBoard(CreateBoardRequest request) {

        Board board = Board.builder()
                .name(request.getName()).build();

        saveBoard(board);
    }

    private void saveBoard(Board board) {
        boardRepository.save(board);
    }

    public void updateBoard(Integer boardId, String name) {

        Board board = getBoardBy(boardId);
        boolean updated = false;

        if (name != null && !name.equals(board.getName())) {
            board.setName(name);
            updated = true;
        }

        if (updated)
            saveBoard(board);
    }

    public Board getBoardBy(Integer boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Board with id = %d does not exists!", boardId))
        );
    }

    public void deleteBoard(Integer boardId) {
        boardRepository.delete(getBoardBy(boardId));
    }
}
