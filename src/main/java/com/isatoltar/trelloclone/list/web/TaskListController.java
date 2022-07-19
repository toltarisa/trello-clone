package com.isatoltar.trelloclone.list.web;

import com.isatoltar.trelloclone.list.business.TaskListService;
import com.isatoltar.trelloclone.list.data.CreateTaskListRequest;
import com.isatoltar.trelloclone.list.data.TaskListDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/boards")
public class TaskListController {

    final TaskListService taskListService;

    /**
     * L1: Creates new task list for board
     *
     * @param boardId   Listenin kaydedileceği board'un id bilgisi
     * @param request   Request payload to create new list
     * @return          HTTP 201
     */
    @PostMapping("/{boardId}/lists")
    public ResponseEntity<TaskListDTO> createTaskList(@PathVariable @Positive Integer boardId,
                                            @RequestBody CreateTaskListRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(taskListService.createTaskList(boardId, request));
    }

    /**
     * L2: Returns all tasklists of given board
     *
     * @param boardId   Board id
     * @return          HTTP 200
     */
    @GetMapping("/{boardId}/lists")
    public ResponseEntity<?> getTaskList(@PathVariable @Positive Integer boardId) {
        List<TaskListDTO> dtoList = taskListService.getTaskListOf(boardId);
        if (dtoList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(dtoList);
    }

    /**
     * L3: Updates task list
     *
     * @param listId    The id of the list to be updated
     * @param name      New name of the list
     * @return          HTTP 204
     */
    @PatchMapping("/{boardId}/lists/{listId}")
    public ResponseEntity<?> updateTaskList(@PathVariable @Positive Integer boardId,
                                            @PathVariable Integer listId,
                                            @RequestParam(required = false) String name) {

        taskListService.updateTaskList(boardId, listId, name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * L4: Deletes task list
     *
     * @param listId    The id of the list to be deleted
     * @return          HTTP 204
     */
    @DeleteMapping("/{boardId}/lists/{listId}")
    public ResponseEntity<?> deleteTaskList(@PathVariable @Positive Integer boardId,
                                            @PathVariable Integer listId) {

        taskListService.deleteTaskList(boardId, listId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}