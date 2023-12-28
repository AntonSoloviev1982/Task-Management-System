package com.solovyev_anton.taskmanagementsystem.services;

import com.solovyev_anton.taskmanagementsystem.dtos.JwtRequest;
import com.solovyev_anton.taskmanagementsystem.dtos.JwtResponse;
import com.solovyev_anton.taskmanagementsystem.dtos.RegisterDto;
import com.solovyev_anton.taskmanagementsystem.mappers.UserMapper;
import com.solovyev_anton.taskmanagementsystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public JwtResponse createAuthToken(JwtRequest jwtRequest) {
        return null;
    }

    public boolean register(RegisterDto registerDto) {
        if (userService.findByUsername(registerDto.getUsername()).isPresent()) {
            return false;
        }
        registerDto.setPassword(encoder.encode(registerDto.getPassword()));
        userRepository.save(userMapper.registerToUser(registerDto));
        return true;
    }

}
