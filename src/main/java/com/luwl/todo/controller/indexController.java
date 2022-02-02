package com.luwl.todo.controller;


import com.luwl.todo.model.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    private TaskRepository taskRepository;

    public indexController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("")
    public String showHomePage(Model model){

        model.addAttribute("numberOfTask",taskRepository.count());
        return "index";
    }


}
