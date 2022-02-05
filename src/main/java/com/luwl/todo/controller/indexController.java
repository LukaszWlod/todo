package com.luwl.todo.controller;


import com.luwl.todo.TaskFilter;
import com.luwl.todo.TaskFormData;
import com.luwl.todo.model.Task;
import com.luwl.todo.repository.TaskRepository;
import com.luwl.todo.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/")
public class indexController {


    @Autowired
    private final TaskServiceImpl taskService;
    private final TaskRepository taskRepository;

    public indexController(TaskServiceImpl taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String index(Model model) {
        addAttributesForIndex(model, TaskFilter.ALL);
        return "index";
    }

    @GetMapping("/active")
    public String indexActive(Model model) {
        addAttributesForIndex(model, TaskFilter.ACTIVE);
        return "index";
    }

    @GetMapping("/completed")
    public String indexCompleted(Model model) {
        addAttributesForIndex(model, TaskFilter.COMPLETED);
        return "index";
    }



    @PostMapping
    public String addNewTask(@Valid @ModelAttribute("task") TaskFormData formData) {
        taskService.saveTask(new Task(formData.getTitle(), false));


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

    @DeleteMapping("/completed")
    public String deleteCompletedItems() {
        List<Task> items = taskService.getTasks( TaskFilter.COMPLETED);
        for (Task item : items) {
            taskService.deleteTaskById(item.getId());
        }
        return "redirect:/";
    }

    private void addAttributesForIndex(Model model,
                                       TaskFilter taskFilter) {
        model.addAttribute("task", new TaskFormData());
        model.addAttribute("filter", taskFilter);
        model.addAttribute("listOfTasks", taskService.getTasks(taskFilter));
        model.addAttribute("numberOfTask", taskService.getAmountOfTasks());
        model.addAttribute("numberOfActiveItems",taskService.getNumberOfActiveTasks());
        model.addAttribute("numberOfCompletedItems",taskService. getNumberOfCompletedTasks());

    }


}
