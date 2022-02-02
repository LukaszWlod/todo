package com.luwl.todo.controller;


import com.luwl.todo.TaskFormData;
import com.luwl.todo.model.Task;
import com.luwl.todo.model.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

@Controller
public class indexController {
    private TaskRepository taskRepository;

    public indexController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("")
    public String showHomePage(Model model){
        model.addAttribute("task", new TaskFormData());
        model.addAttribute("numberOfTask",taskRepository.count());
        return "index";
    }

    public String addNewTask(@Valid @ModelAttribute("task") TaskFormData formData){
        taskRepository.save(new Task(formData.getTitle(), false));

        return "redirect";
    }


}
