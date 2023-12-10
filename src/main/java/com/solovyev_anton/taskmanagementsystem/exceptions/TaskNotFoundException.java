package com.solovyev_anton.taskmanagementsystem.exceptions;

public class TaskNotFoundException extends RuntimeException{

    private final int id;

    public TaskNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Task with id: '%d' is not found!", id);
    }
}
