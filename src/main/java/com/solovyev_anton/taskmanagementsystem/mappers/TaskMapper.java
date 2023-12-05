package com.solovyev_anton.taskmanagementsystem.mappers;

import com.solovyev_anton.taskmanagementsystem.dtos.TaskDto;
import com.solovyev_anton.taskmanagementsystem.entities.Task;
import com.solovyev_anton.taskmanagementsystem.entities.User;
import com.solovyev_anton.taskmanagementsystem.repositories.UserRepository;

import java.security.Principal;

public class TaskMapper {

    private final UserRepository userRepository;

    public TaskMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public Task toEntity(TaskDto taskDto, Principal principal) {
//        User user = userRepository.findByUsername(principal.getName())
//                .orElseThrow();
//    }

}
