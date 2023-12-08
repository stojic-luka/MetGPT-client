package app.controller.components.chat;

import app.api.ChatApiService;
import app.model.chat.Chat;
import app.model.chat.ChatsModel;
import app.model.chat.MessagesModel;
import app.view.components.chat.ChatView;
import app.view.components.chat.SidePaneView;
import javafx.beans.property.SimpleStringProperty;
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
    private final SimpleStringProperty currentChatId;
    private final MessagesModel messagesModel;

    public SidePaneController(SidePaneView sidePaneView, ChatsModel chatsModel, MessagesModel messagesModel, SimpleStringProperty currentChatId) {
        this.chatsModel = chatsModel;
        this.messagesModel = messagesModel;
        this.sidePaneView = sidePaneView;
        this.currentChatId = currentChatId;

        final EventHandler<MouseEvent> changeChat = e -> {
            ChatView chatView = (ChatView) e.getSource();
            Chat chat = (Chat) chatView.getContextMenu().getUserData();
            if (!chat.getChatId().toString().equals(currentChatId.get())) {
                currentChatId.set(chat.getChatId().toString());
            }
        };

        final EventHandler<KeyEvent> renameOnEnter = e -> {
            if (e.getCode() == KeyCode.ENTER) {
                ChatView chatView = (ChatView) ((TextField) e.getSource()).getParent();
                Chat chat = (Chat) chatView.getContextMenu().getUserData();

                chat.setTitle(chatView.getRenameChatField().getText());
                ChatApiService.renameChat(
                        chat.getChatId().toString(),
                        chatView.getRenameChatField().getText()
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
                        chatView.getRenameChatField().setOnKeyPressed(renameOnEnter);
                        chatView.getDeleteItem().setOnAction(deleteLambda);
                        chatView.getContextMenu().setUserData(chat);
                        chatView.getChatTitle().textProperty().bind(chat.titleProperty());
                        chat.setParent(chatView);

                        sidePaneView.getChatsVBox().getChildren().add(chatView);
                    }
                } else if (change.wasRemoved()) {
                    for (Chat chat : change.getRemoved()) {
                        ChatApiService.deleteChat(chat.getChatId().toString());
                        sidePaneView.getChatsVBox().getChildren().remove(chat.getParent());
                        messagesModel.clearMessages();
                    }
                }
            }
        });

        sidePaneView.getAddChatButton().setOnAction(e -> ChatApiService.addChat(chatsModel::addChat, currentChatId::set));

        ChatApiService.loadChats(chatsModel::addChat);
    }
}
