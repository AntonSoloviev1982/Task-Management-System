package com.solovyev_anton.taskmanagementsystem.exceptions;

public class UserNotFoundException extends RuntimeException{

    private final String username;

    public UserNotFoundException(String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return String.format("User with username: '%s' is not found!", username);
    }
}
