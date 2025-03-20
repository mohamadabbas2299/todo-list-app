package com.abbas.todo_list_app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String reminderFrequency; // DAILY, WEEKLY, MONTHLY
    private LocalDateTime reminderTime;
    private String email;
    private String phoneNumber;
    private boolean emailNotification;
    private boolean smsNotification;
    private boolean completed;

    private boolean reminded;

    // ✅ Constructors
    public Task() {}

    public Task(String title, String description, String reminderFrequency, LocalDateTime reminderTime,
                String email, String phoneNumber, boolean emailNotification, boolean smsNotification, boolean completed) {
        this.title = title;
        this.description = description;
        this.reminderFrequency = reminderFrequency;
        this.reminderTime = reminderTime;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.emailNotification = emailNotification;
        this.smsNotification = smsNotification;
        this.completed = completed;
    }
    public boolean isReminded() {
        return reminded;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getReminderFrequency() { return reminderFrequency; }
    public void setReminderFrequency(String reminderFrequency) { this.reminderFrequency = reminderFrequency; }

    public LocalDateTime getReminderTime() { return reminderTime; }
    public void setReminderTime(LocalDateTime reminderTime) { this.reminderTime = reminderTime; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public boolean isEmailNotification() { return emailNotification; }
    public void setEmailNotification(boolean emailNotification) { this.emailNotification = emailNotification; }

    public boolean isSmsNotification() { return smsNotification; }
    public void setSmsNotification(boolean smsNotification) { this.smsNotification = smsNotification; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setReminded(boolean reminded) {
        this.reminded = reminded;
    }
}
