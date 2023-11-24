package app.model.chat;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MessagesModel {
    
    private final ObservableList<Message> messages = FXCollections.observableArrayList();
    private final SimpleListProperty<Message> messagesProperty = new SimpleListProperty<>(messages);

    public ObservableList<Message> getMessages() {
        return messagesProperty.get();
    }

    public SimpleListProperty<Message> getMessagesProperty() {
        return messagesProperty;
    }

    public void addMessage(String content, boolean senderBot) {
        messages.add(new Message(content, senderBot));
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
