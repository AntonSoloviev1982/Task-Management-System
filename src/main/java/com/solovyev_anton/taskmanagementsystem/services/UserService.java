package com.solovyev_anton.taskmanagementsystem.services;

import com.solovyev_anton.taskmanagementsystem.dtos.RegisterDto;
import com.solovyev_anton.taskmanagementsystem.entities.User;
import com.solovyev_anton.taskmanagementsystem.exceptions.UserNotFoundException;
import com.solovyev_anton.taskmanagementsystem.mappers.UserMapper;
import com.solovyev_anton.taskmanagementsystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }


    public boolean register(RegisterDto registerDto) {
        if (findByUsername(registerDto.getUsername()).isPresent()) {
            return false;
        }
        registerDto.setPassword(encoder.encode(registerDto.getPassword()));
        userRepository.save(userMapper.registerToUser(registerDto));
        return true;
    }
}
