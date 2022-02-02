package com.luwl.todo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @GetMapping("")
    public String showHomePage(){
        return "index";
    }
}
