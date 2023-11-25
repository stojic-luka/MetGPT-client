package app.model.chat;

import java.time.LocalDateTime;
import javafx.scene.Node;

public class Chat {
    private String title;
    private final LocalDateTime createdAt;
    
    private Node parent;

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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Chat{" + "title=" + title + ", createdAt=" + createdAt + '}';
    }
}
