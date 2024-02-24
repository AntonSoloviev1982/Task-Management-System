package com.solovyev_anton.taskmanagementsystem.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    private final String username;

    public UserAlreadyExistsException(String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return String.format("User with username: '%s' exists!", username);
    }

}
