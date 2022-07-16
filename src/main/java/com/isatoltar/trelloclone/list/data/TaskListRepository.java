package com.isatoltar.trelloclone.list.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskListRepository extends JpaRepository<TaskList, Integer> {

    @Query("SELECT tl FROM TaskList tl WHERE tl.id = ?1 AND tl.board.id = ?2")
    Optional<TaskList> getByIdAndBoardId(Integer id, Integer boardId);

    @Query("SELECT tl FROM TaskList tl WHERE tl.board.id = ?1")
    Optional<List<TaskList>> getTaskListsOfBoard(Integer boardId);
}