package com.isatoltar.trelloclone.card.business;

import com.isatoltar.trelloclone.card.data.Card;
import com.isatoltar.trelloclone.card.data.CardRepository;
import com.isatoltar.trelloclone.card.data.CreateCardRequest;
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
public class CardService {

    final CardRepository cardRepository;

    /**
     * Creates new Card with given parameters
     *
     * @param request  Card creation request object
     */
    public void createCard(CreateCardRequest request) {

        Card card = new Card();
        card.setTitle(request.getTitle());
        card.setDescription(request.getDescription());

        cardRepository.save(card);
    }

    /**
     * Update Card Information
     *
     * @param cardId        Id of card to be updated
     * @param title         New title of card
     * @param description   New description of card
     */
    public void updateCard(Integer cardId, String title, String description) {

        Card card = getCardById(cardId);
        if (card == null)
            throw new ResourceNotFoundException(
                    String.format("Card with id = %d does not exists", cardId)
            );

        boolean cardUpdated = false;

        if (title != null && !title.equals(card.getTitle())) {
            card.setTitle(title);
            cardUpdated = true;
        }

        if (description != null && !description.equals(card.getDescription())) {
            card.setDescription(description);
            cardUpdated = true;
        }

        if (cardUpdated)
            cardRepository.save(card);

    }

    /**
     * Deletes the given card
     *
     * @param cardId  Id of card to be deleted
     */
    public void deleteCard(Integer cardId) {

        Card card = getCardById(cardId);
        if (card == null)
            throw new ResourceNotFoundException(
                    String.format("Card with id = %d does not exists", cardId)
            );

        cardRepository.delete(card);
    }

    public Card getCardById(Integer id) {
        return cardRepository.findById(id).orElse(null);
    }

    public Boolean doesCardExists(Integer cardId) {
        return cardRepository.existsById(cardId);
    }
}
