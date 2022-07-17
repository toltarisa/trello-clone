package com.isatoltar.trelloclone.card.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    Integer id;
    String title;
    String description;
}
