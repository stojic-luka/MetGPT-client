package app.controller.components.chat;

import app.model.chat.Chat;
import app.model.chat.Message;
import app.model.chat.MessagesModel;
import app.util.NetworkManager;
import app.view.components.ChatComponents.BotMessage;
import app.view.components.chat.ChatView;
import app.view.components.chat.MessagesView;
import app.view.components.chat.PromptView;
import java.util.Map;
import javafx.collections.ListChangeListener;

public class MessagesController {

    private final MessagesModel messagesModel;
    private final MessagesView messagesView;
    private final PromptView promptView;

    public MessagesController(MessagesView messagesView, MessagesModel messagesModel) {
        this.messagesModel = messagesModel;
        this.messagesView = messagesView;
        this.promptView = messagesView.getPromptView();

        this.promptView.getSendButton().setOnAction(e -> {
            String message = this.promptView.getPromptTextArea().getText();
            if (message.isEmpty()) {
                return;
            }

            this.messagesModel.addMessage(message);
            Map<String, String> objMessage = Map.ofEntries(
                    Map.entry("input", message)
            );
            BotMessage botMessage = new BotMessage();
            NetworkManager.sendPostRequestAsync(
                    "http://127.0.0.1:8080/api/v1/chat",
                    objMessage,
                    response -> botMessage.setMessage(response.get("message").getAsString())
            );
        });

        this.messagesModel.getMessages().addListener((ListChangeListener<Message>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Message message : change.getAddedSubList()) {

                    }
                }
            }
//            System.out.println(chatsModel.getChats());
        });
    }
}
