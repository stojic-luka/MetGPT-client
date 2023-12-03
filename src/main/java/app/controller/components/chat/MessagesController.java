package app.controller.components.chat;

import app.model.chat.Message;
import app.model.chat.MessagesModel;
import app.util.NetworkManager;
import app.view.components.chat.BotMessageView;
import app.view.components.chat.UserMessageView;
import app.view.components.chat.MessagesView;
import app.view.components.chat.PromptView;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

public class MessagesController {

    private final MessagesModel messagesModel;
    private final MessagesView messagesView;
    private final PromptView promptView;

    public MessagesController(MessagesView messagesView, MessagesModel messagesModel) {
        this.messagesModel = messagesModel;
        this.messagesView = messagesView;
        this.promptView = messagesView.getPromptView();

        this.promptView.getPromptTextArea().setOnKeyPressed(e -> {
            TextArea textArea = (TextArea) e.getSource();
            if (e.getCode() == KeyCode.ENTER && !e.isShiftDown()) {
                e.consume();
                processMessage();
            } else if (e.getCode() == KeyCode.ENTER && e.isShiftDown()) {
                textArea.appendText("\n");
            }

            if (textArea.getText().length() > 200) {
                textArea.setText(textArea.getText().substring(0, 200));
            }
        });
        this.promptView.getSendButton().setOnAction(e -> processMessage());

        this.messagesModel.getMessages().addListener((ListChangeListener<Message>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Message message : change.getAddedSubList()) {
                        if (!message.isSenderBot()) {
                            messagesView.getMessagesVBox().getChildren().add(new UserMessageView(message.getContent()));
                        } else {
                            messagesView.getMessagesVBox().getChildren().add(new BotMessageView(message.getContent()));
                        }
                    }
                }
            }
        });
    }

    private void processMessage() {
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
                    String msg = response.get("response").getAsString();
                    Platform.runLater(() -> this.messagesModel.addMessage(msg, true));
                }
        );
    }

    public void clearAndLoadMessages(long chatId) {
        this.clearMessages();
        this.loadMessages(chatId);
    }
    
    private void loadMessages(long chatId) {
        NetworkManager.sendGetRequestAsync(
                String.format("http://127.0.0.1:8080/api/v1/chats/%d/messages", chatId),
                response -> {
                    for (JsonElement responseJsonElement : response.getAsJsonArray("messages")) {
                        JsonObject responseJsonObject = responseJsonElement.getAsJsonObject();
                        String createdAtString = responseJsonObject.getAsJsonPrimitive("createdAt").getAsString();
                        
                        Message chat = new Message(
                                responseJsonObject.get("content").getAsString(),
                                responseJsonObject.get("senderBot").getAsBoolean(),
                                LocalDateTime.parse(createdAtString, DateTimeFormatter.ISO_DATE_TIME)
                        );
                        
                        Platform.runLater(() -> messagesModel.addMessage(chat));
                    }
                }
        );
    }

    private void clearMessages() {
        messagesModel.getMessages().clear();
        messagesView.getMessagesVBox().getChildren().clear();
    }
}
