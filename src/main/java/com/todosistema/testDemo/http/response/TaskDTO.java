package com.todosistema.testDemo.http.response;

import com.todosistema.testDemo.entity.Employee;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class TaskDTO implements Serializable {
    private Integer id;
    private Employee employee;
    private String taskName;
    private String description;
    private LocalDateTime execution;
    private String status;
    private LocalDateTime createdAt;
}
