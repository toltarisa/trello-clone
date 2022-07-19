package com.isatoltar.trelloclone.card.data;

import com.isatoltar.trelloclone.comment.data.Comment;
import com.isatoltar.trelloclone.list.data.TaskList;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String title;

    @Column
    String description;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "list_id", referencedColumnName = "id")
    TaskList taskList;
}
