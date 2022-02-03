package com.luwl.todo.service;

import com.luwl.todo.model.Task;
import com.luwl.todo.repository.TaskRepository;

import java.util.List;

public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;


    @Override
    public List<Task> getTasks() {

       return taskRepository.findAll();
       
    }
}
