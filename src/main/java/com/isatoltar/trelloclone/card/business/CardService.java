package com.isatoltar.trelloclone.card.business;

import com.isatoltar.trelloclone.card.data.Card;
import com.isatoltar.trelloclone.card.data.CardRepository;
import com.isatoltar.trelloclone.card.data.CreateCardRequest;
import com.isatoltar.trelloclone.list.business.TaskListService;
import com.isatoltar.trelloclone.list.data.TaskList;
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

    final TaskListService taskListService;

    /**
     * Creates new Card with given parameters
     *
     * @param request  Card creation request object
     */
    public void createCard(CreateCardRequest request) {

        Integer listId = request.getListId();
        TaskList taskList = taskListService.getTaskListBy(listId);

        Card card = Card.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .taskList(taskList).build();

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
        cardRepository.delete(getCardById(cardId));
    }

    public Card getCardById(Integer cardId) {
        return cardRepository.findById(cardId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Card with id = %d does not exists", cardId)
        ));
    }

    public Boolean doesCardExists(Integer cardId) {
        return cardRepository.existsById(cardId);
    }
}
