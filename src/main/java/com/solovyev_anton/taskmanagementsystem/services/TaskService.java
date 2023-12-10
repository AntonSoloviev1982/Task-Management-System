package com.solovyev_anton.taskmanagementsystem.services;

import com.solovyev_anton.taskmanagementsystem.dtos.TaskDtoIn;
import com.solovyev_anton.taskmanagementsystem.dtos.TaskDtoOut;
import com.solovyev_anton.taskmanagementsystem.dtos.UpdateTaskDto;
import com.solovyev_anton.taskmanagementsystem.entities.Status;
import com.solovyev_anton.taskmanagementsystem.entities.Task;
import com.solovyev_anton.taskmanagementsystem.entities.User;
import com.solovyev_anton.taskmanagementsystem.exceptions.TaskNotFoundException;
import com.solovyev_anton.taskmanagementsystem.exceptions.UserNotFoundException;
import com.solovyev_anton.taskmanagementsystem.mappers.TaskMapper;
import com.solovyev_anton.taskmanagementsystem.repositories.TaskRepository;
import com.solovyev_anton.taskmanagementsystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskMapper taskMapper;


    public void createTask(TaskDtoIn taskDtoIn, Principal principal) {
        Task task = taskMapper.toEntity(taskDtoIn, principal);
        taskRepository.save(task);
    }

    public TaskDtoOut getTask(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toTaskDtoOut(task);
    }

    public List<TaskDtoOut> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> taskMapper.toTaskDtoOut(task))
                .collect(Collectors.toList());
    }

    public void updateTask(Integer id, UpdateTaskDto updateTaskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setTitle(updateTaskDto.getTitle());
        task.setDescription(updateTaskDto.getDescription());
        task.setPriority(updateTaskDto.getPriority());
        taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    public void setStatus(Integer id, Status status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(status);
        taskRepository.save(task);
    }

    public void setPerform(Integer id, String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setPerformer(user);
        taskRepository.save(task);
    }

    public List<TaskDtoOut> getAllTasksByAuthor(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        List<Task> tasks = taskRepository.findAllByAuthor(user);
        return tasks.stream()
                .map(task -> taskMapper.toTaskDtoOut(task))
                .collect(Collectors.toList());
    }

    public List<TaskDtoOut> getAllTasksByPerformer(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        List<Task> tasks = taskRepository.findAllByPerformer(user);
        return tasks.stream()
                .map(task -> taskMapper.toTaskDtoOut(task))
                .collect(Collectors.toList());
    }

    public Task findById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
