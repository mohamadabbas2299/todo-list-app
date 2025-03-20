package com.abbas.todo_list_app.service;

import com.abbas.todo_list_app.model.Task;
import com.abbas.todo_list_app.repository.TaskRepository;
import com.abbas.todolist.todolist.service.TaskService;
import com.abbas.todo_list_app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setReminderFrequency(updatedTask.getReminderFrequency());
            task.setReminderTime(updatedTask.getReminderTime());
            task.setEmail(updatedTask.getEmail());
            task.setPhoneNumber(updatedTask.getPhoneNumber());
            task.setEmailNotification(updatedTask.isEmailNotification());
            task.setSmsNotification(updatedTask.isSmsNotification());
            task.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Task not found!");
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"));
    }
}
