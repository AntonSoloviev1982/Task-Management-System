package com.solovyev_anton.taskmanagementsystem.dtos;

import com.solovyev_anton.taskmanagementsystem.entities.Priority;
import lombok.Data;

@Data
public class UpdateTaskDto {

    private String title;

    private String description;

    private Priority priority;

}
