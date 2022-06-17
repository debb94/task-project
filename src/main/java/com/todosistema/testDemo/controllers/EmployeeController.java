package com.todosistema.testDemo.controllers;

import com.todosistema.testDemo.entity.Employee;
import com.todosistema.testDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Integer id){
        var res = employeeService.findById(id).get();
        System.out.println("------------------->"+res.toString());
        return new ResponseEntity<Employee>(res, HttpStatus.OK);
    }


    @GetMapping("/create")
    public Employee create(){
        var employee = new Employee();
        employee.setActive(1);
        employee.setName("Eduardo Barrera");
        return employeeService.save(employee);
    }

}
