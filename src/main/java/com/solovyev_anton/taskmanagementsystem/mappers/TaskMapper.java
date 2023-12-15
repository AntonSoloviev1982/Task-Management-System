package com.solovyev_anton.taskmanagementsystem.mappers;

import com.solovyev_anton.taskmanagementsystem.dtos.TaskDtoIn;
import com.solovyev_anton.taskmanagementsystem.dtos.TaskDtoOut;
import com.solovyev_anton.taskmanagementsystem.entities.Task;
import com.solovyev_anton.taskmanagementsystem.entities.User;
import com.solovyev_anton.taskmanagementsystem.exceptions.UserByUsernameNotFoundException;
import com.solovyev_anton.taskmanagementsystem.exceptions.UserNotFoundException;
import com.solovyev_anton.taskmanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final UserService userService;
    private final CommentMapper commentMapper;


    public Task toEntity(TaskDtoIn taskDtoIn, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserByUsernameNotFoundException(username));
        Task task = new Task();
        task.setTitle(taskDtoIn.getTitle());
        task.setDescription(taskDtoIn.getDescription());
        task.setStatus(taskDtoIn.getStatus());
        task.setPriority(taskDtoIn.getPriority());
        task.setAuthor(user);
        return task;
    }

    public TaskDtoOut toTaskDtoOut(Task task) {
        TaskDtoOut taskDtoOut = new TaskDtoOut();
        taskDtoOut.setTitle(task.getTitle());
        taskDtoOut.setDescription(task.getDescription());
        taskDtoOut.setStatus(task.getStatus());
        taskDtoOut.setPriority(task.getPriority());
        taskDtoOut.setComments(
                task.getComments().stream()
                        .map(commentMapper::toCommentDto)
                        .collect(Collectors.toList()));
        return taskDtoOut;
    }

}
