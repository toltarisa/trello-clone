package com.isatoltar.trelloclone.card.business;

import com.isatoltar.trelloclone.card.data.*;
import com.isatoltar.trelloclone.list.business.TaskListService;
import com.isatoltar.trelloclone.shared.exception.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardService {

    final CardRepository cardRepository;
    final CardDtoConverter cardDtoConverter;
    final TaskListService taskListService;

    public CardDTO createCard(Integer listId, CreateCardRequest request) {
        Card card = Card.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .taskList(taskListService.getTaskListBy(listId))
                .build();

        return cardDtoConverter.convertTo(cardRepository.save(card));
    }

    public List<CardDTO> getCards(Integer listId) {
        List<Card> cards = cardRepository.getCardsOfList(listId)
                .orElse(Collections.emptyList());

        return cards.stream()
                .map(card -> new CardDTO(card.getId(), card.getTaskList().getId(), card.getTitle(), card.getDescription()))
                .collect(Collectors.toList());
    }

    public void updateCard(Integer listId, Integer cardId, String title, String description) {
        Card card = getCardBy(listId, cardId);
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

    public void deleteCard(Integer cardId) {
        cardRepository.delete(getCardBy(cardId));
    }

    public Card getCardBy(Integer cardId) {
        return cardRepository.findById(cardId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Card with id = %d does not exists", cardId)
        ));
    }

    public Boolean doesCardExists(Integer cardId) {
        return cardRepository.existsById(cardId);
    }

    private Card getCardBy(Integer listId, Integer cardId) {
        return cardRepository.getByIdAndListId(cardId, listId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Card with id = %d and list id = %d does not exists", cardId, listId)
        ));
    }
}