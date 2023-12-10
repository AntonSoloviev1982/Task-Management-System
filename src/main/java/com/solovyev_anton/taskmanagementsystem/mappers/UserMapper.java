package com.solovyev_anton.taskmanagementsystem.mappers;

import com.solovyev_anton.taskmanagementsystem.dtos.RegisterDto;
import com.solovyev_anton.taskmanagementsystem.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User registerToUser(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setRole(registerDto.getRole());
        return user;
    }

}
