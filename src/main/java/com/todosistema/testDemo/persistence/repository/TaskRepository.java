package com.todosistema.testDemo.persistence.repository;

import com.todosistema.testDemo.entity.Employee;
import com.todosistema.testDemo.entity.Task;
import com.todosistema.testDemo.persistence.crud.IEmployeeCrudRepository;
import com.todosistema.testDemo.persistence.crud.ITaskCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository{

    @Autowired
    private ITaskCrudRepository taskCrudRepository;

    public List<Task> getAll(){
        return (List<Task>) taskCrudRepository.findAll();
//        var list = taskCrudRepository.findAll();
//        list.forEach(t -> {
//                t.setEmployee(employeeCrudRepository
//                        .findById(t.getEmployeeId())
//                        .orElse(new Employee()));
//                }
//        );
//        return (List<Task>) list;
    }

    public Optional<Task> getTask(Integer id){
        return taskCrudRepository.findById(id);
    }

    public Task save(Task task){
        return taskCrudRepository.save(task);
    }

    public Task update(Integer id, Task task){
        task.setId(id);
        return taskCrudRepository.save(task);
    }

    public void delete(Integer id){
        taskCrudRepository.deleteById(id);
    }
}
