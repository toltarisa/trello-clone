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

    @GetMapping
    public ResponseEntity<?> getBoards(Principal principal) {

        String username = principal.getName();
        List<BoardDto> dtoList = boardService.getAllBoardsOfUser(username);

        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<?> createBoard(Principal principal,
                                         @RequestBody CreateBoardRequest request) {

        String username = principal.getName();
        boardService.createBoard(request, username);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<?> updateBoard(@PathVariable Integer boardId,
                                         @RequestParam(required = false) String name) {

        boardService.updateBoard(boardId, name);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable Integer boardId) {

        boardService.deleteBoard(boardId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
