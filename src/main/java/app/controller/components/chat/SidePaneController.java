package app.controller.components.chat;

import app.model.chat.Chat;
import app.model.chat.ChatsModel;
import app.view.components.chat.ChatView;
import app.view.components.chat.SidePaneView;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class SidePaneController {

    private final ChatsModel chatsModel;
    private final SidePaneView sidePaneView;
    private final MessagesController messagesController;

    public SidePaneController(SidePaneView sidePaneView, ChatsModel chatsModel, MessagesController messagesController) {
        this.chatsModel = chatsModel;
        this.sidePaneView = sidePaneView;
        this.messagesController = messagesController;

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
                        chatView.getRenameItem().setOnAction(renameLambda);
                        chatView.getDeleteItem().setOnAction(deleteLambda);
                        chatView.getContextMenu().setUserData(chat);
                        chat.setParent(chatView);

                        sidePaneView.getChatsVBox().getChildren().add(chatView);
                    }
                } else if (change.wasRemoved()) {
                    for (Chat chat : change.getRemoved()) {
                        sidePaneView.getChatsVBox().getChildren().remove(chat.getParent());
                    }
                }
            }
            System.out.println(chatsModel.getChats());
        });

        sidePaneView.getAddChatButton().setOnAction(e -> {
            chatsModel.addChat("add chat test");
        });

    }
}
