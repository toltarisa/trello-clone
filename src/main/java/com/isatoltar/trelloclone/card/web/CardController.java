package com.isatoltar.trelloclone.card.web;

import com.isatoltar.trelloclone.card.business.CardService;
import com.isatoltar.trelloclone.card.data.CreateCardRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/lists")
public class CardController {

    final CardService cardService;

    /**
     * C1: Creates new card
     *
     * @param listId    Id of the list where the card will be saved
     * @param request   New card informations
     * @return          HTTP 201
     */
    @PostMapping("/{listId}/cards")
    public ResponseEntity<?> createCard(@PathVariable @Positive Integer listId,
                                        @RequestBody CreateCardRequest request) {

        cardService.createCard(listId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * C2: List cards of list
     *
     * @param listId    Id of the list where the card will be saved
     * @return          HTTP 201
     */
    @GetMapping("/{listId}/cards")
    public ResponseEntity<?> getCards(@PathVariable @Positive Integer listId) {
        return ResponseEntity.ok(
                cardService.getCards(listId)
        );
    }

    /**
     * C3: Updates the card with given id
     *
     * @param listId        The id of the list where the card will be updated
     * @param cardId        The id of the card to be updated
     * @param title         New title of the card
     * @param description   New description of the card
     * @return              HTTP 204
     */
    @PatchMapping("/{listId}/cards/{cardId}")
    public ResponseEntity<?> updateCard(@PathVariable @Positive Integer listId,
                                        @PathVariable Integer cardId,
                                        @RequestParam(required = false) String title,
                                        @RequestParam(required = false) String description) {

        cardService.updateCard(listId, cardId, title, description);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * C4: Deletes the card with given id
     *
     * @param cardId    The id of the card to be deleted
     * @return          HTTP 204
     */
    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Integer cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}