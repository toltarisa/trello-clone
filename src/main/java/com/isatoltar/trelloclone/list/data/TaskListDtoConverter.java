package com.isatoltar.trelloclone.list.data;

import org.springframework.stereotype.Component;

@Component
public class TaskListDtoConverter {

    public TaskListDTO convertTo(TaskList taskList) {
        return new TaskListDTO(taskList.getId(), taskList.getName());
    }
}
