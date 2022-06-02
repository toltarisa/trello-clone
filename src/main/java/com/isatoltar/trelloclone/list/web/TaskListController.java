package com.isatoltar.trelloclone.list.web;

import com.isatoltar.trelloclone.list.business.TaskListService;
import com.isatoltar.trelloclone.list.data.CreateTaskListRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/task-lists")
public class TaskListController {

    final TaskListService taskListService;

    @PostMapping
    public ResponseEntity<?> createTaskList(@RequestBody CreateTaskListRequest request) {

        taskListService.createTaskList(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{listId}")
    public ResponseEntity<?> updateTaskList(@PathVariable Integer listId,
                                            @RequestParam(required = false) String name) {

        taskListService.updateTaskList(listId, name);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<?> deleteTaskList(@PathVariable Integer listId) {

        taskListService.deleteTaskList(listId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
