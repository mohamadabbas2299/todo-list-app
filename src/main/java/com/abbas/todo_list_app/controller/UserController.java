package com.abbas.todo_list_app.controller;

import com.abbas.todo_list_app.model.User;
import com.abbas.todo_list_app.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // ✅ Show Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // ✅ Handle Login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", username);
            return "redirect:/tasks";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    // ✅ Show Registration Page
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // ✅ Handle Registration
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }

        userRepository.save(user);
        model.addAttribute("successMessage", "User created successfully! Redirecting...");
        return "success"; // Redirect to success page
    }

    // ✅ Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
