package app.model.chat;

import java.time.LocalDateTime;
import javafx.scene.Node;

public class Chat {
    private long chatId;
    private String title;
    private final LocalDateTime createdAt;
    
    private Node parent;

    public Chat(long chatId, String title) {
        this.chatId = chatId;
        this.title = title;
        this.createdAt = LocalDateTime.now();
    }

    public Chat(long chatId, String title, LocalDateTime createdAt) {
        this.chatId = chatId;
        this.title = title;
        this.createdAt = createdAt;
    }

    public long getChatId() {
        return chatId;
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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Chat{" + "chatId=" + chatId + ", title=" + title + ", createdAt=" + createdAt + '}';
    }
}
