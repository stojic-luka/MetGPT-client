package app.controller.components.chat;

import app.model.chat.MessagesModel;
import app.view.components.chat.MessagesView;
import app.view.components.chat.PromptView;

public class MessagesController {

    private final MessagesModel messagesModel;
    private final MessagesView messagesView;
    private final PromptView promptView;
    
    public MessagesController(MessagesView messagesView, MessagesModel messagesModel) {
        this.messagesModel = messagesModel;
        this.messagesView = messagesView;
        this.promptView = messagesView.getPromptView();
        
        
    }
}
