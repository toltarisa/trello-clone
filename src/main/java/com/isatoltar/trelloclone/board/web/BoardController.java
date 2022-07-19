package com.isatoltar.trelloclone.board.web;

import com.isatoltar.trelloclone.board.business.BoardService;
import com.isatoltar.trelloclone.board.data.BoardDto;
import com.isatoltar.trelloclone.board.data.CreateBoardRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/boards")
public class BoardController {

    final BoardService boardService;

    /**
     * B1: List boards of current user
     *
     * @return  HTTP 200
     */
    @GetMapping
    public ResponseEntity<?> getBoards(Principal principal) {
        String username = principal.getName();
        List<BoardDto> dtoList = boardService.getAllBoardsOfUser(username);
        return ResponseEntity.ok(dtoList);
    }

    /**
     * B2: Creates task board
     *
     * @param request   Payload to create Task Board
     * @return          HTTP 201
     */
    @PostMapping
    public ResponseEntity<?> createBoard(Principal principal,
                                         @RequestBody CreateBoardRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(boardService.createBoard(request, principal.getName()));
    }

    /**
     * B3: Updates task board partially
     *
     * @param boardId   The id of the board to be updated
     * @param name      new name of the board
     * @return          HTTP 204
     */
    @PatchMapping("/{boardId}")
    public ResponseEntity<?> updateBoard(@PathVariable Integer boardId,
                                         @RequestParam(required = false) String name) {

        boardService.updateBoard(boardId, name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * B4: Deletes the board with given board id
     *
     * @param boardId   The id of the board to be deleted
     * @return          HTTP 204
     */
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable Integer boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}