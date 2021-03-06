package com.luwl.todo.service;

import com.luwl.todo.TaskFilter;
import com.luwl.todo.model.Task;
import com.luwl.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{




    private TaskRepository taskRepository;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = null;
        if(optionalTask.isPresent()){
            task = optionalTask.get();
            taskRepository.deleteById(id);
        }
        else{
           throw new RuntimeException("Nie znaleziono zadania o id= " + id);
        }
    }

    @Override
    public void changeTaskStatus(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = null;
        if (optionalTask.isPresent()) {

            task = optionalTask.get();
            task.setCompleted(!task.isCompleted());
            taskRepository.save(task);
        } else {

            throw new RuntimeException("Nie zadania o id : " + id);
        }

    }

    @Override
    public Long getAmountOfTasks(){
      return   taskRepository.count();
    }


    public int getNumberOfActiveTasks(){
       return taskRepository.countAllByCompleted(false);
    }


    public int getNumberOfCompletedTasks() {
        return taskRepository.countAllByCompleted(true);
    }

    @Override
    public List<Task> getTasks(TaskFilter filter) {
        return switch (filter) {
            case ALL -> taskRepository.findAll();
            case ACTIVE -> taskRepository.findAllByCompleted(false);
            case COMPLETED -> taskRepository.findAllByCompleted(true);
        };
    }


}
