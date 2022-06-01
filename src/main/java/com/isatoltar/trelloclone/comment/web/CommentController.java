package com.isatoltar.trelloclone.comment.web;

import com.isatoltar.trelloclone.comment.business.CommentService;
import com.isatoltar.trelloclone.comment.data.CreateCommentRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentController {

    final CommentService commentService;

    @PostMapping("/{cardId}/comments")
    public ResponseEntity<?> createComment(@RequestBody CreateCommentRequest request,
                                           @PathVariable Integer cardId) {

        commentService.createComment(cardId, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{cardId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Integer cardId,
                                           @PathVariable Integer commentId,
                                           @RequestParam(required = false) String content) {

        commentService.updateComment(cardId, commentId, content);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{cardId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer cardId,
                                           @PathVariable Integer commentId) {

        commentService.deleteComment(cardId, commentId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
