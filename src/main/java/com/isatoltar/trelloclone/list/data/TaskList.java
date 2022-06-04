package com.isatoltar.trelloclone.list.data;

import com.isatoltar.trelloclone.board.data.Board;
import com.isatoltar.trelloclone.card.data.Card;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lists")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String name;

    @OneToMany(mappedBy = "taskList", cascade = CascadeType.REMOVE)
    Set<Card> cards;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    Board board;
}
