package com.isatoltar.trelloclone.list.business;

import com.isatoltar.trelloclone.board.business.BoardService;
import com.isatoltar.trelloclone.list.data.CreateTaskListRequest;
import com.isatoltar.trelloclone.list.data.TaskList;
import com.isatoltar.trelloclone.list.data.TaskListDTO;
import com.isatoltar.trelloclone.list.data.TaskListRepository;
import com.isatoltar.trelloclone.shared.exception.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskListService {

    final TaskListRepository taskListRepository;

    final BoardService boardService;

    public void createTaskList(Integer boardId, CreateTaskListRequest request) {
        TaskList taskList = TaskList.builder()
                .name(request.getName())
                .board(boardService.getBoardBy(boardId))
                .build();

        taskListRepository.save(taskList);
    }

    public List<TaskListDTO> getTaskListOf(Integer boardId) {
        List<TaskList> taskList = taskListRepository.getTaskListsOfBoard(boardId)
                .orElse(Collections.emptyList());

        return taskList.stream()
                .map(list -> new TaskListDTO(list.getId(), list.getName(), boardId))
                .collect(Collectors.toList());
    }

    public void updateTaskList(Integer boardId, Integer listId, String name) {
        TaskList taskList = getTaskListBy(listId, boardId);
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

    public void deleteTaskList(Integer boardId, Integer listId) {
        taskListRepository.delete(getTaskListBy(listId, boardId));
    }

    private TaskList getTaskListBy(Integer listId, Integer boardId) {
        return taskListRepository.getByIdAndBoardId(listId, boardId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Task list with id = %d and board id = %d does not exists!", listId, boardId)
                ));
    }
}