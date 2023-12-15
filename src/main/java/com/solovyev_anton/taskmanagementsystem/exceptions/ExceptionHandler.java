package com.solovyev_anton.taskmanagementsystem.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handlerUserNotFound(UserNotFoundException e) {
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserByUsernameNotFoundException.class)
    public ResponseEntity<String> handlerUserNotFound(UserByUsernameNotFoundException e) {
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handlerTaskNotFound(TaskNotFoundException e) {
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<String> handlerCommentNotFound(CommentNotFoundException e) {
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
