package com.solovyev_anton.taskmanagementsystem.services;

import com.solovyev_anton.taskmanagementsystem.dtos.JwtRequest;
import com.solovyev_anton.taskmanagementsystem.dtos.JwtResponse;
import com.solovyev_anton.taskmanagementsystem.dtos.RegisterDto;
import com.solovyev_anton.taskmanagementsystem.exceptions.IncorrectLoginOrPasswordException;
import com.solovyev_anton.taskmanagementsystem.exceptions.UserAlreadyExistsException;
import com.solovyev_anton.taskmanagementsystem.mappers.UserMapper;
import com.solovyev_anton.taskmanagementsystem.repositories.UserRepository;
import com.solovyev_anton.taskmanagementsystem.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;


    public JwtResponse createAuthToken(JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw  new IncorrectLoginOrPasswordException();
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }

    public void register(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        if (userService.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException(username);
        }
        registerDto.setPassword(encoder.encode(registerDto.getPassword()));
        userRepository.save(userMapper.registerToUser(registerDto));
    }

}
