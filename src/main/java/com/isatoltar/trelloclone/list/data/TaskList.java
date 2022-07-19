package com.isatoltar.trelloclone.list.data;

import com.isatoltar.trelloclone.board.data.Board;
import com.isatoltar.trelloclone.card.data.Card;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lists")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String name;

    @OneToMany(mappedBy = "taskList", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    Set<Card> cards;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    Board board;
}
