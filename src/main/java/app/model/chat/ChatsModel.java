package app.model.chat;

import java.util.UUID;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChatsModel {

    private final ObservableList<Chat> chats = FXCollections.observableArrayList();
    private final SimpleListProperty<Chat> chatsProperty = new SimpleListProperty<>(chats);

    public ObservableList<Chat> getChats() {
        return chatsProperty.get();
    }

    public SimpleListProperty<Chat> getChatsProperty() {
        return chatsProperty;
    }

    public void addChat(UUID chatId, String title) {
        addChat(new Chat(chatId, title));
    }

    public void addChat(String chatId, String title) {
        addChat(new Chat(chatId, title));
    }

    public void addChat(Chat chat) {
        chats.add(chat);
    }

    public void addChat(Chat[] chatArr) {
        chats.addAll(chatArr);
    }

    public void removeChat(Chat chat) {
        if (chats.contains(chat)) {
            chats.remove(chat);
        }
    }
}
