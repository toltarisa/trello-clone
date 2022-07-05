package com.isatoltar.trelloclone.board.data;

import com.isatoltar.trelloclone.auth.data.User;
import com.isatoltar.trelloclone.list.data.TaskList;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "boards")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String name;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    Set<TaskList> tasks;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
}
