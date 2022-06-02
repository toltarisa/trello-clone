package com.isatoltar.trelloclone.comment.web;

import com.isatoltar.trelloclone.comment.business.CommentService;
import com.isatoltar.trelloclone.comment.data.CreateCommentRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/cards")
public class CommentController {

    final CommentService commentService;

    @PostMapping("/{cardId}/comments")
    public ResponseEntity<?> createComment(@RequestBody CreateCommentRequest request,
                                           @PathVariable Integer cardId) {

        commentService.createComment(cardId, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{cardId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Integer cardId,
                                           @PathVariable Integer commentId,
                                           @RequestParam(required = false) String content) {

        commentService.updateComment(cardId, commentId, content);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{cardId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer cardId,
                                           @PathVariable Integer commentId) {

        commentService.deleteComment(cardId, commentId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
