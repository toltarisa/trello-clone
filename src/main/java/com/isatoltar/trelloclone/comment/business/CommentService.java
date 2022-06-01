package com.isatoltar.trelloclone.comment.business;

import com.isatoltar.trelloclone.card.business.CardService;
import com.isatoltar.trelloclone.card.data.Card;
import com.isatoltar.trelloclone.comment.data.Comment;
import com.isatoltar.trelloclone.comment.data.CommentRepository;
import com.isatoltar.trelloclone.comment.data.CreateCommentRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentService {

    final CommentRepository commentRepository;

    final CardService cardService;

    public void createComment(Integer cardId, CreateCommentRequest request) {

        Card card = cardService.getCardById(cardId);
        if (card == null) {
            //:TODO Throw card not found exception
            return;
        }

        Comment comment = new Comment();
        comment.setCard(card);
        comment.setContent(request.getContent());
        comment.setUserId(request.getUserId());

        saveComment(comment);
    }

    public void updateComment(Integer cardId, Integer commentId, String content) {

        Boolean cardExists = cardService.doesCardExists(cardId);
        if (!cardExists) {
            //: TODO Throw exception for card not found
            return;
        }

        Comment comment = getCommentBy(commentId);
        if (comment == null) {
            //: TODO Throw excepton for comment not found
            return;
        }

        if (content != null && !content.equals(comment.getContent())) {
            comment.setContent(content);
            comment.setUpdatedAt(LocalDateTime.now());
            saveComment(comment);
        }
    }

    public void deleteComment(Integer cardId, Integer commentId) {

        Boolean cardExists = cardService.doesCardExists(cardId);
        if (!cardExists) {
            //: TODO Throw exception for card not found
            return;
        }

        Comment comment = getCommentBy(commentId);
        if (comment == null) {
            //: TODO Throw excepton for comment not found
            return;
        }

        commentRepository.delete(comment);

    }

    public Comment getCommentBy(Integer commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    private void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
