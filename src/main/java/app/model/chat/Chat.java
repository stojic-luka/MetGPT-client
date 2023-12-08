package app.model.chat;

import java.time.LocalDateTime;
import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public class Chat {

    private final UUID chatId;
    private final StringProperty title;
    private final LocalDateTime createdAt;

    private Node parent;

    public Chat(UUID chatId, String title) {
        this.chatId = chatId;
        this.title = new SimpleStringProperty(title);
        this.createdAt = LocalDateTime.now();
    }

    public Chat(String chatId, String title) {
        this.chatId = UUID.fromString(chatId);
        this.title = new SimpleStringProperty(title);
        this.createdAt = LocalDateTime.now();
    }

    public Chat(UUID chatId, String title, LocalDateTime createdAt) {
        this.chatId = chatId;
        this.title = new SimpleStringProperty(title);
        this.createdAt = createdAt;
    }

    public Chat(String chatId, String title, LocalDateTime createdAt) {
        this.chatId = UUID.fromString(chatId);
        this.title = new SimpleStringProperty(title);
        this.createdAt = createdAt;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public UUID getChatId() {
        return chatId;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
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
