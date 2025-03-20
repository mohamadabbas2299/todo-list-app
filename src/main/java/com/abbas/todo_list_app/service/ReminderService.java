package com.abbas.todo_list_app.service;

import com.abbas.todo_list_app.model.Task;
import com.abbas.todo_list_app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReminderService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private NotificationService notificationService;

    // ✅ Scheduled task to check and send reminders every minute
    @Scheduled(cron = "0 * * * * *") // Runs every minute
    public void checkAndSendReminders() {
        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = taskRepository.findByReminderTimeBeforeAndRemindedFalse(now);  // ✅ Only fetch unreminded tasks

        for (Task task : tasks) {
            sendReminder(task);
            task.setReminded(true);  // ✅ Mark the task as reminded
            taskRepository.save(task);
        }
    }

    private void sendReminder(Task task) {
        String message = "Reminder: " + task.getTitle() + " - " + task.getDescription();

        if (task.isEmailNotification() && task.getEmail() != null) {
            notificationService.sendEmail(task.getEmail(), "Task Reminder", message);
        }

        if (task.isSmsNotification() && task.getPhoneNumber() != null) {
            notificationService.sendSms(task.getPhoneNumber(), message);
        }
    }
}
