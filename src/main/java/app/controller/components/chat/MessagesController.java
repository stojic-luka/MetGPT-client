package app.controller.components.chat;

import app.model.chat.Message;
import app.model.chat.MessagesModel;
import app.util.NetworkManager;
import app.view.components.chat.BotMessage;
import app.view.components.chat.UserMessage;
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
            String messageString = this.promptView.getPromptTextArea().getText().trim();
            if (messageString.isEmpty()) {
                return;
            }

            this.promptView.getPromptTextArea().clear();

            this.messagesModel.addMessage(messageString);
            NetworkManager.sendPostRequestAsync(
                    "http://127.0.0.1:8080/api/v1/chat",
                    Map.ofEntries(Map.entry("input", messageString)),
                    response -> {
                        String msg = response.get("message").getAsString();
                        System.out.println(msg);
                        // fix sending message from main thread
                        this.messagesModel.addMessage(msg, true);
                        System.out.println(msg);
                    }
            );
        });

        this.messagesModel.getMessages().addListener((ListChangeListener<Message>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    System.out.println("added");
                    for (Message message : change.getAddedSubList()) {
                        if (!message.isSenderBot()) {
                            messagesView.getMessagesVBox().getChildren().add(new UserMessage(message.getContent()));
                        } else {
                            messagesView.getMessagesVBox().getChildren().add(new BotMessage(message.getContent()));
                        }
                    }
                }
            }
            System.out.println(messagesModel.getMessages());
        });
    }
}
