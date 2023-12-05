package com.solovyev_anton.taskmanagementsystem.controllers;

import com.solovyev_anton.taskmanagementsystem.dtos.TaskDto;
import com.solovyev_anton.taskmanagementsystem.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto, Principal principal) {
        taskService.createTask(taskDto, principal);
        return ResponseEntity.ok().build();

    }
}
