package com.todosistema.testDemo.controllers;

import com.todosistema.testDemo.entity.Task;
import com.todosistema.testDemo.http.response.TaskDTO;
import com.todosistema.testDemo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public ResponseEntity<List<TaskDTO>> getAll(){
        return new ResponseEntity<List<TaskDTO>>(taskService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("id") Integer id){
        return taskService.getTask(id).map(task->{
            return new ResponseEntity<TaskDTO>(task,HttpStatus.OK);
        }).orElse(new ResponseEntity<TaskDTO>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Task> save(@RequestBody Task task){
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus("Pendiente");
        return new ResponseEntity<Task>(taskService.save(task),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@RequestBody Task task, @PathVariable Integer id){
        return new ResponseEntity<>(taskService.update(id,task),HttpStatus.OK);
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity updateStatus(@PathVariable Integer id){
        try{
            taskService.updateStatus(id,"Completada");
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        return new ResponseEntity(taskService.delete(id),HttpStatus.OK);
    }
}
