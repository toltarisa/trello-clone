package com.isatoltar.trelloclone.card.web;

import com.isatoltar.trelloclone.card.business.CardService;
import com.isatoltar.trelloclone.card.data.CreateCardRequest;
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
public class CardController {

    final CardService cardService;

    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody CreateCardRequest request) {

        cardService.createCard(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{cardId}")
    public ResponseEntity<?> updateCard(@PathVariable Integer cardId,
                                        @RequestParam(required = false) String title,
                                        @RequestParam(required = false) String description) {

        cardService.updateCard(cardId, title, description);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Integer cardId) {

        cardService.deleteCard(cardId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
