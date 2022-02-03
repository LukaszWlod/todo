package com.luwl.todo.controller;


import com.luwl.todo.TaskFormData;
import com.luwl.todo.model.Task;
import com.luwl.todo.model.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class indexController {
    private TaskRepository taskRepository;

    public indexController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("")
    public String showHomePage(Model model) {
        model.addAttribute("task", new TaskFormData());
        model.addAttribute("listOfTasks", getTasks());
        model.addAttribute("numberOfTask", taskRepository.count());

        return "index";
    }

    @PostMapping
    public String addNewTask(@Valid @ModelAttribute("task") TaskFormData formData) {
        taskRepository.save(new Task(formData.getTitle(), false));

        return "redirect:/";
    }

    private List<Task> getTasks() {

        List<Task>  taskList = taskRepository.findAll();
        for ( var task: taskList) {
            System.out.println(task.getId());
            System.out.println(task.getTitle());
        }
        return taskRepository.findAll();
    }


    @PutMapping("/{id}/toggle")
    public String toggleSelection(@PathVariable("id") Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = null;
        if (optionalTask.isPresent()) {

            task = optionalTask.get();
            task.setCompleted(!task.isCompleted());
            taskRepository.save(task);
        } else {

            throw new RuntimeException("Nie zadania o id : " + id);
        }



        return "redirect:/";
    }


}
