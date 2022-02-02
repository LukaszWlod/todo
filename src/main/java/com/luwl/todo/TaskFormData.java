package com.luwl.todo;

import javax.validation.constraints.NotBlank;

public class TaskFormData {

   @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

