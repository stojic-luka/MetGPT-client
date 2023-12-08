package app.controller;

import app.controller.components.chat.MessagesController;
import app.controller.components.chat.SidePaneController;
import app.model.chat.ChatsModel;
import app.model.chat.MessagesModel;
import app.view.ChatTabView;
import javafx.beans.property.SimpleStringProperty;

public class ChatTabController {

    private final ChatsModel chatsModel;
    private final MessagesModel messagesModel;
    private final ChatTabView chatTabView;
    private final SidePaneController sidePaneController;
    private final MessagesController messagesController;

    private final SimpleStringProperty currentChatId;

    public ChatTabController(ChatTabView chatTabView) {
        this.currentChatId = new SimpleStringProperty(null);
        
        this.chatsModel = new ChatsModel();
        this.messagesModel = new MessagesModel();
        this.chatTabView = chatTabView;
        this.messagesController = new MessagesController(chatTabView.getMessagesView(), chatsModel, messagesModel, currentChatId);
        this.sidePaneController = new SidePaneController(chatTabView.getSidePaneView(), chatsModel, messagesModel, currentChatId);
        
        currentChatId.addListener((observable, oldValue, newValue) -> {
            messagesModel.clearMessages();
            messagesController.loadMessages(newValue);
        });
    }
}
