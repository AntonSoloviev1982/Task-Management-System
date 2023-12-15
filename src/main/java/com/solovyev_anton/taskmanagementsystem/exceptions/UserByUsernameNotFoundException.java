package com.solovyev_anton.taskmanagementsystem.exceptions;

public class UserByUsernameNotFoundException extends RuntimeException{

    private final String username;

    public UserByUsernameNotFoundException(String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return String.format("User with username: '%s' is not found!", username);
    }
}
