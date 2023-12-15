package com.solovyev_anton.taskmanagementsystem.dtos;

import com.solovyev_anton.taskmanagementsystem.entities.Comment;
import com.solovyev_anton.taskmanagementsystem.entities.Priority;
import com.solovyev_anton.taskmanagementsystem.entities.Status;
import lombok.Data;

import java.util.List;

@Data
public class TaskDtoOut {

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private List<CommentDto> comments;

}
