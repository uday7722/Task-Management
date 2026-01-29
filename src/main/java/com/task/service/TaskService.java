package com.task.service;

import com.task.dto.TaskDto;
import com.task.utils.enums.TaskStatus;
import jakarta.validation.Valid;

import java.util.List;

public interface TaskService {
    TaskDto createTask(@Valid TaskDto taskDto);

    List<TaskDto> findAllTasks();

    TaskDto findTaskById(Long id);

    TaskDto updateTaskStatus(Long id, TaskStatus status);

    TaskDto deleteById(Long id);
}
