package com.solovyev_anton.taskmanagementsystem.controllers;

import com.solovyev_anton.taskmanagementsystem.dtos.TaskDtoIn;
import com.solovyev_anton.taskmanagementsystem.dtos.TaskDtoOut;
import com.solovyev_anton.taskmanagementsystem.dtos.UpdateTaskDto;
import com.solovyev_anton.taskmanagementsystem.entities.Status;
import com.solovyev_anton.taskmanagementsystem.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Создание нового задания",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Новое задание",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TaskDtoIn.class)
                    )
            )
    )
    @PostMapping()
    public ResponseEntity<?> createTask(
            @RequestBody TaskDtoIn taskDtoIn,
            @Parameter(description = "текущий вошедший в систему пользователь")
            Principal principal) {
        taskService.createTask(taskDtoIn, principal);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Поиск задания по идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденное задание",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TaskDtoOut.class)
                            )
                    )
            })
    @GetMapping("{id}")
    public ResponseEntity<TaskDtoOut> getTask(
            @Parameter(description = "Идентификатор задания")
            @PathVariable Integer id) {
        TaskDtoOut taskDtoOut = taskService.getTask(id);
        return ResponseEntity.ok().body(taskDtoOut);
    }

    @Operation(summary = "Поиск всех заданий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все найденные задания",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = TaskDtoOut.class))
                            )
                    )
            })
    @GetMapping
    public ResponseEntity<List<TaskDtoOut>> getAllTasks(
            @Parameter(description = "Номер страницы")
            @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "Количество элементов на странице")
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        List<TaskDtoOut> tasks = taskService.getAllTasks(PageRequest.of(page, size));
        return ResponseEntity.ok().body(tasks);
    }

    @Operation(summary = "Изменение задания",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Измененное задание",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UpdateTaskDto.class)
                    )
            )
    )
    @PatchMapping("{id}")
    public ResponseEntity<?> updateTask(
            @Parameter(description = "Идентификатор задания")
            @PathVariable Integer id,
            @RequestBody UpdateTaskDto updateTaskDto) {
        taskService.updateTask(id, updateTaskDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удаление задания",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(
            @Parameter(description = "Идентификатор задания")
            @PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Изменение статуса задания",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            })
    @PatchMapping("{id}/status")
    public ResponseEntity<?> setStatus(
            @Parameter(description = "Идентификатор задания")
            @PathVariable Integer id,
            @Parameter(description = "Новый статус")
            @RequestParam Status status) {
        taskService.setStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Изменение исполнителя задания",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            })
    @PatchMapping("{taskId}/performer/{userId}")
    public ResponseEntity<?> setPerformer(
            @Parameter(description = "Идентификатор задания")
            @PathVariable Integer taskId,
            @Parameter(description = "Идентификатор исполнителя")
            @PathVariable Integer userId) {
        taskService.setPerform(taskId, userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Поиск всех заданий по автору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все найденные задания автора",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = TaskDtoOut.class))
                            )
                    )
            })
    @GetMapping("{id}/author")
    public ResponseEntity<List<TaskDtoOut>> getAllTasksByAuthor(
            @Parameter(description = "Идентификатор автора")
            @PathVariable Integer id,
            @Parameter(description = "Номер страницы")
            @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "Количество элементов на странице")
            @RequestParam(required = false, defaultValue = "10") int size) {
        List<TaskDtoOut> tasks = taskService.getAllTasksByAuthor(PageRequest.of(page, size), id);
        return ResponseEntity.ok().body(tasks);
    }

    @Operation(summary = "Поиск всех заданий по исполнителю",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все найденные задания исполнителя",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = TaskDtoOut.class))
                            )
                    )
            })
    @GetMapping("{id}/performer")
    public ResponseEntity<List<TaskDtoOut>> getAllTasksByPerformer(
            @Parameter(description = "Идентификатор исполнителя")
            @PathVariable Integer id,
            @Parameter(description = "Номер страницы")
            @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "Количество элементов на странице")
            @RequestParam(required = false, defaultValue = "10") int size) {
        List<TaskDtoOut> tasks = taskService.getAllTasksByPerformer(PageRequest.of(page, size), id);
        return ResponseEntity.ok().body(tasks);
    }


}
