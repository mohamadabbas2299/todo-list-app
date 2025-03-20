package com.abbas.todo_list_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // ✅ Enables Spring Scheduler
public class TodoListAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoListAppApplication.class, args);
	}
}
