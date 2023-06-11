package com.project.todoapp.service;

import com.project.todoapp.exceptions.ToDoExceptions;
import com.project.todoapp.mapper.TaskDTOToTask;
import com.project.todoapp.persistence.entity.Task;
import com.project.todoapp.persistence.entity.TaskStatus;
import com.project.todoapp.persistence.repository.TaskRepository;
import com.project.todoapp.service.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskDTOToTask mapper;

    public Task createTask(TaskDTO taskDTO) {
        Task task = mapper.map(taskDTO);
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findAllByStatus(TaskStatus status) {
        return taskRepository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskFinished(Long id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        taskRepository.updateFinished(id);
    }

    public void deleteTask(Long id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        taskRepository.deleteById(id);
    }
}
