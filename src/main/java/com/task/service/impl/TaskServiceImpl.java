package com.task.service.impl;

import com.task.dto.TaskDto;
import com.task.exception.NoDataFoundException;
import com.task.mapper.TaskMapper;
import com.task.model.Task;
import com.task.repo.TaskRepository;
import com.task.service.TaskService;
import com.task.utils.enums.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }


    @Override
    public TaskDto createTask(TaskDto taskDto) {

        Task task = taskMapper.toEntity(taskDto);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);

    }

    @Override
    public List<TaskDto> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (tasks.isEmpty()) {
            throw new NoDataFoundException("No tasks found");
        }

        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public TaskDto findTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new NoDataFoundException("No task found with id " + id)
                );

        return taskMapper.toDto(task);

    }

    @Override
    public TaskDto updateTaskStatus(Long id, TaskStatus status) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new NoDataFoundException("No task found with id " + id)
                );

        task.setTaskStatus(status);

        Task updatedTask = taskRepository.save(task);

        return taskMapper.toDto(updatedTask);
    }

    @Override
    public TaskDto deleteById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new NoDataFoundException("No task found with id " + id)
                );
        taskRepository.delete(task);

        return taskMapper.toDto(task);


    }


}

