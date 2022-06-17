package com.todosistema.testDemo.persistence.crud;

import com.todosistema.testDemo.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeCrudRepository extends CrudRepository<Employee, Integer> {
}
