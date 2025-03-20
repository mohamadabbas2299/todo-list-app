package com.abbas.todo_list_app.repository;

import com.abbas.todo_list_app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByReminderTimeBeforeAndRemindedFalse(LocalDateTime now);  // ✅ Fetch only unreminded tasks
}
