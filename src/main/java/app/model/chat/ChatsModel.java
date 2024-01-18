package app.model.chat;

import java.util.UUID;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChatsModel {
    private final ObservableList<Chat> chats = FXCollections.observableArrayList();
    private final SimpleListProperty<Chat> chatsProperty = new SimpleListProperty<>(chats);

    /**
     * Retrieves the list of chats.
     *
     * @return  The list of chats.
     */
    public ObservableList<Chat> getChats() {
        return chatsProperty.get();
    }

    /**
     * Retrieves the property that holds the list of Chat objects.
     *
     * @return the property containing the list of Chat objects
     */
    public SimpleListProperty<Chat> getChatsProperty() {
        return chatsProperty;
    }

    /**
     * Adds a chat with the specified chat ID and title to the observable list.
     *
     * @param  chatId  the ID of the chat
     * @param  title   the title of the chat
     */
    public void addChat(UUID chatId, String title) {
        addChat(new Chat(chatId, title));
    }

    /**
     * Adds a chat to the observable list.
     *
     * @param  chatId    the ID of the chat
     * @param  title     the title of the chat
     */
    public void addChat(String chatId, String title) {
        addChat(new Chat(chatId, title));
    }

    /**
     * Adds a chat to the observable list.
     *
     * @param  chat  the chat object to be added
     */
    public void addChat(Chat chat) {
        chats.add(chat);
    }

    /**
     * Adds an array of Chats to the observable list.
     *
     * @param  chatArr  an array of Chat objects to be added
     */
    public void addChat(Chat[] chatArr) {
        chats.addAll(chatArr);
    }

    /**
     * Removes the specified chat from the observable list.
     *
     * @param  chat  the chat to be removed
     */
    public void removeChat(Chat chat) {
        if (chats.contains(chat)) {
            chats.remove(chat);
        }
    }
}
