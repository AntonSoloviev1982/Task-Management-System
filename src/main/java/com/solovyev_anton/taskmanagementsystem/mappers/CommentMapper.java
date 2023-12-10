package com.solovyev_anton.taskmanagementsystem.mappers;

import com.solovyev_anton.taskmanagementsystem.dtos.CommentDto;
import com.solovyev_anton.taskmanagementsystem.entities.Comment;
import com.solovyev_anton.taskmanagementsystem.entities.Task;
import com.solovyev_anton.taskmanagementsystem.exceptions.TaskNotFoundException;
import com.solovyev_anton.taskmanagementsystem.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final TaskService taskService;


    public Comment toEntity(Integer taskId, CommentDto commentDto) {
        Task task = taskService.findById(taskId);
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setTask(task);
        return comment;
    }

}
