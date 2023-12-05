package com.solovyev_anton.taskmanagementsystem.dtos;

import com.solovyev_anton.taskmanagementsystem.entities.Priority;
import com.solovyev_anton.taskmanagementsystem.entities.Status;
import lombok.Data;

@Data
public class TaskDto {
private String title;
private String description;
private Status status;
private Priority priority;

}
