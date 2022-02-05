package com.luwl.todo.service;

import com.luwl.todo.model.Task;

import java.util.List;

public interface TaskService {
    List<Task>  getTasks();
    void safeTask();
    void deleteTaskById(Long id);
    void changeTaskStatus(Long id);
    Long getAmountOfTasks();
}
