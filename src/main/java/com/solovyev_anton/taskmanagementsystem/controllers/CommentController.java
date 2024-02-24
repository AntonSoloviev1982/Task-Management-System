package com.solovyev_anton.taskmanagementsystem.controllers;

import com.solovyev_anton.taskmanagementsystem.dtos.CommentDto;
import com.solovyev_anton.taskmanagementsystem.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Создание нового комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Новый комментарий",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDto.class)
                    )
            )
    )
    @PostMapping("{taskId}")
    public ResponseEntity<?> createComment(@Parameter(description = "Идентификатор задания")
                                           @PathVariable Integer taskId,
                                           @RequestBody CommentDto commentDto) {
        commentService.createComment(taskId, commentDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Изменение комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Измененный комментарий",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDto.class)
                    )
            )
    )
    @PatchMapping("{id}")
    public ResponseEntity<?> updateComment(@Parameter(description = "Идентификатор комментария")
                                           @PathVariable Integer id,
                                           @RequestBody CommentDto commentDto) {
        commentService.updateComment(id, commentDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удаление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteComment(@Parameter(description = "Идентификатор комментария")
                                           @PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

}
