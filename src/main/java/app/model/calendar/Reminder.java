package app.model.calendar;

import java.time.LocalDateTime;

public class Reminder {

    private String content;
    private LocalDateTime assignedTime;
    private final LocalDateTime createdAt;

    public Reminder(String content, LocalDateTime assignedTime) {
        this.content = content;
        this.assignedTime = assignedTime;
        this.createdAt = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(LocalDateTime assignedTime) {
        this.assignedTime = assignedTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Reminder{" + "content=" + content + ", assignedTime=" + assignedTime + ", createdAt=" + createdAt + '}';
    }
}
