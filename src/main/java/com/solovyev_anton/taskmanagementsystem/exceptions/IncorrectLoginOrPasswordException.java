package com.solovyev_anton.taskmanagementsystem.exceptions;

public class IncorrectLoginOrPasswordException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Incorrect login or password!";
    }

}
