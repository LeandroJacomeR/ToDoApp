package com.project.todoapp.mapper;

import com.project.todoapp.persistence.entity.Task;
import com.project.todoapp.persistence.entity.TaskStatus;
import com.project.todoapp.service.dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskDTOToTask implements IMapper<TaskDTO, Task>{
    @Override
    public Task map(TaskDTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }
}
