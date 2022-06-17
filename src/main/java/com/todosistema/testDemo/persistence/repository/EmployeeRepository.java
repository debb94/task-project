package com.todosistema.testDemo.persistence.repository;

import com.todosistema.testDemo.entity.Employee;
import com.todosistema.testDemo.persistence.crud.IEmployeeCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {
    @Autowired
    private IEmployeeCrudRepository employeeCrudRepository;

    public List<Employee> getAll(){
        return (List<Employee>) employeeCrudRepository.findAll();
    }

    public Optional<Employee> findById(Integer id){
        return employeeCrudRepository.findById(id);
    }

    public Employee save(Employee employee){
        return employeeCrudRepository.save(employee);
    }



}
