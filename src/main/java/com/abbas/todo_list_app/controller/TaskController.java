package com.abbas.todo_list_app.controller;

import com.abbas.todo_list_app.model.Task;
import com.abbas.todo_list_app.repository.TaskRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    // ✅ Restrict task page to logged-in users
    @GetMapping
    public String listTasks(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/auth/login"; // Redirect if not logged in
        }
        model.addAttribute("tasks", taskRepository.findAll());
        return "task-list";
    }

    // ✅ Show create task form
    @GetMapping("/new")
    public String showCreateTaskForm(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("task", new Task());
        return "task-form";
    }

    // ✅ Save task
    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/auth/login";
        }
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    // ✅ Delete task
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/auth/login";
        }
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }
}
