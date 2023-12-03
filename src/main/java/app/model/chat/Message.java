package app.model.chat;

import java.time.LocalDateTime;

public class Message {

    private final String content;
    private final boolean senderBot;
    private final LocalDateTime createdAt;

    public Message(String content, boolean senderBot) {
        this.content = content;
        this.senderBot = senderBot;
        this.createdAt = LocalDateTime.now();
    }

    public Message(String content, boolean senderBot, LocalDateTime createdAt) {
        this.content = content;
        this.senderBot = senderBot;
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public boolean isSenderBot() {
        return senderBot;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Message{" + "content=" + content + ", senderBot=" + senderBot + ", createdAt=" + createdAt + '}';
    }
}