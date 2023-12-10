package com.solovyev_anton.taskmanagementsystem.controllers;

import com.solovyev_anton.taskmanagementsystem.dtos.RegisterDto;
import com.solovyev_anton.taskmanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if (userService.register(registerDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
