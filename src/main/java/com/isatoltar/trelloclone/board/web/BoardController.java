package com.isatoltar.trelloclone.board.web;

import com.isatoltar.trelloclone.board.business.BoardService;
import com.isatoltar.trelloclone.board.data.CreateBoardRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/boards")
public class BoardController {

    final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody CreateBoardRequest request) {

        boardService.createBoard(request);

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
