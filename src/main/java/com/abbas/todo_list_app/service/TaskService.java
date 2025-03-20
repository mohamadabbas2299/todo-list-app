package com.abbas.todolist.todolist.service;

import com.abbas.todo_list_app.model.Task;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
}
