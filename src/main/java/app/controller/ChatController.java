package app.controller;

import app.controller.components.chat.MessagesController;
import app.controller.components.chat.SidePaneController;
import app.model.chat.ChatsModel;
import app.model.chat.MessagesModel;
import app.view.ChatTabView;

public class ChatController {

    private final ChatsModel chatsModel;
    private final MessagesModel messagesModel;
    private final ChatTabView chatTabView;
    private final SidePaneController sidePaneController;
    private final MessagesController messagesController;
    
    public ChatController(ChatTabView chatTabView) {
        this.chatsModel = new ChatsModel();
        this.messagesModel = new MessagesModel();
        this.chatTabView = chatTabView;
        this.sidePaneController = new SidePaneController(chatTabView.getSidePaneView(), chatsModel);
        this.messagesController = new MessagesController(chatTabView.getMessagesView(), messagesModel);
    }
}
