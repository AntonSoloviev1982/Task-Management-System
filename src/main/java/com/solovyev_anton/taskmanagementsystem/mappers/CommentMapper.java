package com.solovyev_anton.taskmanagementsystem.mappers;

import com.solovyev_anton.taskmanagementsystem.dtos.CommentDto;
import com.solovyev_anton.taskmanagementsystem.entities.Comment;
import com.solovyev_anton.taskmanagementsystem.entities.Task;
import com.solovyev_anton.taskmanagementsystem.services.TaskService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private final TaskService taskService;

    public CommentMapper(@Lazy TaskService taskService) {
        this.taskService = taskService;
    }


    public Comment toEntity(Integer taskId, CommentDto commentDto) {
        Task task = taskService.findById(taskId);
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setTask(task);
        return comment;
    }

    public CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setText(comment.getText());
        return commentDto;
    }

}
