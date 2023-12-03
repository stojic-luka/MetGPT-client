package app.model.chat;

import java.time.LocalDateTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public class Chat {

    private final long chatId;
    private final StringProperty title;
    private final LocalDateTime createdAt;

    private Node parent;

    public Chat(long chatId, String title) {
        this.chatId = chatId;
        this.title = new SimpleStringProperty(title);
        this.createdAt = LocalDateTime.now();
    }

    public Chat(long chatId, String title, LocalDateTime createdAt) {
        this.chatId = chatId;
        this.title = new SimpleStringProperty(title);
        this.createdAt = createdAt;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public long getChatId() {
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
