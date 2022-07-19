package com.isatoltar.trelloclone.card.data;

import org.springframework.stereotype.Component;

@Component
public class CardDtoConverter {

    public CardDTO convertTo(Card card) {
        return new CardDTO(card.getId(), card.getTaskList().getId(), card.getTitle(), card.getDescription());
    }

    public Card convertTo(CardDTO dto) {
        return Card.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }
}
