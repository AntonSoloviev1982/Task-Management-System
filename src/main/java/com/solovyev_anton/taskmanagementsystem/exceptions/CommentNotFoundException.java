package com.solovyev_anton.taskmanagementsystem.exceptions;

public class CommentNotFoundException extends RuntimeException{

    private final int id;

    public CommentNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Comment with id: '%d' is not found!", id);
    }
}
