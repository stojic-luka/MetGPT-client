package app.model.chat;

import java.time.LocalDateTime;

public class Chat {
    private String title;
    private final LocalDateTime createdAt;

    public Chat(String title) {
        this.title = title;
        this.createdAt = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Chat{" + "title=" + title + ", createdAt=" + createdAt + '}';
    }
}
