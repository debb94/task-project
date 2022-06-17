package com.todosistema.testDemo.service;

import com.todosistema.testDemo.entity.Task;
import com.todosistema.testDemo.http.response.TaskDTO;
import com.todosistema.testDemo.persistence.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeService employeeService;

    public List<TaskDTO> getAll(){
        var response = new ArrayList<TaskDTO>();
        taskRepository.getAll().stream().forEach(t->{
            response.add(TaskDTO.builder()
                            .id(t.getId())
                            .taskName(t.getTaskName())
                            .description(t.getTaskName())
                            .employee(employeeService.findById(t.getEmployeeId()).orElse(null))
                            .createdAt(t.getCreatedAt())
                            .execution(t.getExecution())
                            .status(t.getStatus())
                    .build());
        });
        return response;
    }

    public Optional<TaskDTO> getTask(Integer id){
        return Optional.ofNullable(taskRepository.getTask(id).map(t->{
            return TaskDTO.builder()
                    .id(t.getId())
                    .taskName(t.getTaskName())
                    .description(t.getDescription())
                    .status(t.getStatus())
                    .execution(t.getExecution())
                    .createdAt(t.getCreatedAt())
                    .employee(employeeService.findById(t.getEmployeeId()).orElse(null))
                    .build();
        }).orElse(null));
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public Task update(Integer id, Task task){
        var old = taskRepository.getTask(id);
        task.setCreatedAt(old.get().getCreatedAt());
        return taskRepository.update(id,task);
    }

    public void updateStatus(Integer id, String status){
        var task = taskRepository.getTask(id).get();
        task.setStatus(status);
        task = taskRepository.update(id,task);
    }

    public Boolean delete(Integer id){
        return taskRepository.getTask(id).map(task ->{
            taskRepository.delete(id);
            return true;
        }).orElse(false);
    }
}
