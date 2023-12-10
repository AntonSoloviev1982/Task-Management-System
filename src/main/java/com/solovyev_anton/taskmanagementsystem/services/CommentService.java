package com.solovyev_anton.taskmanagementsystem.services;

import com.solovyev_anton.taskmanagementsystem.dtos.CommentDto;
import com.solovyev_anton.taskmanagementsystem.entities.Comment;
import com.solovyev_anton.taskmanagementsystem.exceptions.CommentNotFoundException;
import com.solovyev_anton.taskmanagementsystem.mappers.CommentMapper;
import com.solovyev_anton.taskmanagementsystem.repositories.CommentRepository;
import com.solovyev_anton.taskmanagementsystem.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;


    public void createComment(Integer taskId, CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(taskId, commentDto);
        commentRepository.save(comment);
    }

    public void updateComment(Integer id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }
}
