package com.solovyev_anton.taskmanagementsystem.exceptions;

public class UserNotFoundException extends RuntimeException{

    private final Integer id;

    public UserNotFoundException(Integer id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("User with id: '%d' is not found!", id);
    }
}
