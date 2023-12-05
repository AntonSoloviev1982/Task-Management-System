package com.solovyev_anton.taskmanagementsystem.services;

import com.solovyev_anton.taskmanagementsystem.dtos.TaskDto;
import com.solovyev_anton.taskmanagementsystem.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(TaskDto taskDto, Principal principal) {

    }
}
