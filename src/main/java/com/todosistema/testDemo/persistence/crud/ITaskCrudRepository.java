package com.todosistema.testDemo.persistence.crud;

import com.todosistema.testDemo.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface ITaskCrudRepository extends CrudRepository<Task,Integer> {

}
