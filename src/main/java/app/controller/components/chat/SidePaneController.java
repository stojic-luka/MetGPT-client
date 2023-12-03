package app.controller.components.chat;

import app.model.chat.Chat;
import app.model.chat.ChatsModel;
import app.util.NetworkManager;
import app.view.components.chat.ChatView;
import app.view.components.chat.SidePaneView;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;
import javafx.application.Platform;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class SidePaneController {

    private final ChatsModel chatsModel;
    private final SidePaneView sidePaneView;
    private final SimpleLongProperty currentChatId;

    public SidePaneController(SidePaneView sidePaneView, ChatsModel chatsModel, SimpleLongProperty currentChatId) {
        this.chatsModel = chatsModel;
        this.sidePaneView = sidePaneView;
        this.currentChatId = currentChatId;

        final EventHandler<MouseEvent> changeChat = e -> {
            ChatView chatView = (ChatView) e.getSource();
            Chat chat = (Chat) chatView.getContextMenu().getUserData();

            if (chat.getChatId() != currentChatId.get()) {
                currentChatId.set(chat.getChatId());
            }
        };

        final EventHandler<KeyEvent> sendOnEnter = e -> {
            if (e.getCode() == KeyCode.ENTER) {
                ChatView chatView = (ChatView) ((TextField) e.getSource()).getParent();
                Chat chat = (Chat) chatView.getContextMenu().getUserData();

                chat.setTitle(chatView.getRenameChatField().getText());
                NetworkManager.sendPutRequestAsync(
                        String.format("http://127.0.0.1:8080/api/v1/chats/%d", chat.getChatId()),
                        Map.ofEntries(Map.entry("title", chatView.getRenameChatField().getText()))
                );
                chatView.toggleRename();
            }
        };

        final EventHandler<ActionEvent> renameLambda = e -> {
            MenuItem menuItem = (MenuItem) e.getSource();
            Chat chat = (Chat) menuItem.getParentPopup().getUserData();
            ChatView chatView = (ChatView) chat.getParent();
            chatView.toggleRename();
        };

        final EventHandler<ActionEvent> deleteLambda = e -> {
            MenuItem menuItem = (MenuItem) e.getSource();
            Chat chat = (Chat) menuItem.getParentPopup().getUserData();
            chatsModel.getChats().remove(chat);
        };

        chatsModel.getChats().addListener((ListChangeListener<Chat>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Chat chat : change.getAddedSubList()) {
                        ChatView chatView = new ChatView(chat.getTitle());
                        chatView.setOnMousePressed(changeChat);
                        chatView.getRenameItem().setOnAction(renameLambda);
                        chatView.getRenameChatField().setOnKeyPressed(sendOnEnter);
                        chatView.getDeleteItem().setOnAction(deleteLambda);
                        chatView.getContextMenu().setUserData(chat);
                        chatView.getChatTitle().textProperty().bind(chat.titleProperty());
                        chat.setParent(chatView);

                        sidePaneView.getChatsVBox().getChildren().add(chatView);
                    }
                } else if (change.wasRemoved()) {
                    for (Chat chat : change.getRemoved()) {
                        NetworkManager.sendDeleteRequestAsync(
                                String.format("http://127.0.0.1:8080/api/v1/chats/%d", chat.getChatId())
                        );

                        sidePaneView.getChatsVBox().getChildren().remove(chat.getParent());
                    }
                } else if (change.wasUpdated()) {
                    for (int i = change.getFrom(); i <= change.getTo(); i++) {
                        Chat updatedChat = chatsModel.getChats().get(i);
                        System.out.println("Updated chat: " + updatedChat.getTitle());
                    }
                }
            }
        });

        sidePaneView.getAddChatButton().setOnAction(e -> {
            NetworkManager.sendPostRequestAsync(
                    "http://127.0.0.1:8080/api/v1/chats",
                    Map.ofEntries(Map.entry("title", "New chat"))
            );

            chatsModel.addChat(new Random().nextInt(100) + 1, "New chat");
        });

        loadChats();
    }

    private void loadChats() {
        NetworkManager.sendGetRequestAsync(
                "http://127.0.0.1:8080/api/v1/chats",
                response -> {
                    for (JsonElement responseJsonElement : response.getAsJsonArray("chats")) {
                        JsonObject responseJsonObject = responseJsonElement.getAsJsonObject();
                        String createdAtString = responseJsonObject.getAsJsonPrimitive("createdAt").getAsString();

                        Chat chat = new Chat(
                                responseJsonObject.get("chatId").getAsLong(),
                                responseJsonObject.get("title").getAsString(),
                                LocalDateTime.parse(createdAtString, DateTimeFormatter.ISO_DATE_TIME)
                        );

                        Platform.runLater(() -> chatsModel.addChat(chat));
                    }
                }
        );
    }
}
