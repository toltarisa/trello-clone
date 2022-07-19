package com.isatoltar.trelloclone.list.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListDTO {
    Integer id;
    String name;
    Integer boardId;

    public TaskListDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
