package com.isatoltar.trelloclone.list.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Integer> {
}
