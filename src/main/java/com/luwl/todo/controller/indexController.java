package com.luwl.todo.controller;


import com.luwl.todo.TaskFormData;
import com.luwl.todo.model.Task;
import com.luwl.todo.repository.TaskRepository;
import com.luwl.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class indexController {
    @Autowired
    private TaskService taskService;
    private TaskRepository taskRepository;

    public indexController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("")
    public String showHomePage(Model model) {
        model.addAttribute("task", new TaskFormData());
        model.addAttribute("listOfTasks", taskService.getTasks());
        model.addAttribute("numberOfTask", taskService.getAmountOfTasks());
        

        return "index";
    }

    @PostMapping
    public String addNewTask(@Valid @ModelAttribute("task") TaskFormData formData) {
        taskRepository.save(new Task(formData.getTitle(), false));


        return "redirect:/";
    }


    @PutMapping("/{id}/toggle")
    public String toggleSelection(@PathVariable("id") Long id) {

        taskService.changeTaskStatus(id);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id")Long id){
        taskService.deleteTaskById(id);
        return "redirect:/";
    }




}
