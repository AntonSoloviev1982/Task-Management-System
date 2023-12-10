package com.solovyev_anton.taskmanagementsystem.controllers;

import com.solovyev_anton.taskmanagementsystem.dtos.TaskDtoIn;
import com.solovyev_anton.taskmanagementsystem.dtos.TaskDtoOut;
import com.solovyev_anton.taskmanagementsystem.dtos.UpdateTaskDto;
import com.solovyev_anton.taskmanagementsystem.entities.Status;
import com.solovyev_anton.taskmanagementsystem.entities.Task;
import com.solovyev_anton.taskmanagementsystem.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PostMapping()
    public ResponseEntity<?> createTask(@RequestBody TaskDtoIn taskDtoIn, Principal principal) {
        taskService.createTask(taskDtoIn, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDtoOut> getTask(@PathVariable Integer id) {
        TaskDtoOut taskDtoOut = taskService.getTask(id);
        return ResponseEntity.ok().body(taskDtoOut);
    }

    @GetMapping
    public ResponseEntity<List<TaskDtoOut>> getAllTasks() {
        List<TaskDtoOut> tasks = taskService.getAllTasks();
        return ResponseEntity.ok().body(tasks);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateTask(@PathVariable Integer id,
                                        @RequestBody UpdateTaskDto updateTaskDto) {
        taskService.updateTask(id, updateTaskDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/status")
    public ResponseEntity<?> setStatus(@PathVariable Integer id,
                                       @RequestParam Status status) {
        taskService.setStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/performer")
    public ResponseEntity<?> setPerformer(@PathVariable Integer id,
                                          @RequestParam String username) {
        taskService.setPerform(id, username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("author")
    public ResponseEntity<List<TaskDtoOut>> getAllTasksByAuthor(Principal principal) {
        List<TaskDtoOut> tasks = taskService.getAllTasksByAuthor(principal);
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("performer")
    public ResponseEntity<List<TaskDtoOut>> getAllTasksByPerformer(Principal principal) {
        List<TaskDtoOut> tasks = taskService.getAllTasksByPerformer(principal);
        return ResponseEntity.ok().body(tasks);
    }




}
