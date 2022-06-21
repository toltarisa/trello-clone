package com.isatoltar.trelloclone.list.business;

import com.isatoltar.trelloclone.list.data.CreateTaskListRequest;
import com.isatoltar.trelloclone.list.data.TaskList;
import com.isatoltar.trelloclone.list.data.TaskListRepository;
import com.isatoltar.trelloclone.shared.exception.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskListService {

    final TaskListRepository taskListRepository;

    public void createTaskList(CreateTaskListRequest request) {

        TaskList taskList = TaskList.builder()
                .name(request.getName())
                .build();

        taskListRepository.save(taskList);
    }

    public void updateTaskList(Integer listId, String name) {

        TaskList taskList = getTaskListBy(listId);

        if (name != null && !name.equals(taskList.getName())) {
            taskList.setName(name);
            taskListRepository.save(taskList);
        }
    }

    public TaskList getTaskListBy(Integer listId) {
        return taskListRepository.findById(listId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Task list with id = %d does not exists!", listId)
        ));
    }

    public void deleteTaskList(Integer listId) {
        taskListRepository.delete(getTaskListBy(listId));
    }
}
