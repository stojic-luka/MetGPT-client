package app.model.chat;

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

    public void addChat(String title) {
        chats.add(new Chat(title));
    }

    public void addChat(Chat chat) {
        chats.add(chat);
    }

    public void removeChat(String title) {
        for (Chat chat : chats) {
            if (chat.getTitle().equals(title)) {
                chats.remove(chat);
            }
        }
    }

    public void removeChat(Chat chat) {
        if (chats.contains(chat)) {
            chats.remove(chat);
        }
    }
}
