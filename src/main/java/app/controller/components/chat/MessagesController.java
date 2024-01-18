package app.controller.components.chat;

import app.api.ChatApiService;
import app.model.chat.ChatsModel;
import app.model.chat.Message;
import app.model.chat.MessagesModel;
import app.view.components.chat.BotMessageView;
import app.view.components.chat.MessagesView;
import app.view.components.chat.PromptView;
import app.view.components.chat.UserMessageView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

public class MessagesController {
    private final MessagesModel messagesModel;
    private final ChatsModel chatsModel;
    private final MessagesView messagesView;
    private final PromptView promptView;

    private final SimpleStringProperty currentChatId;

    public MessagesController(MessagesView messagesView, ChatsModel chatsModel, MessagesModel messagesModel, SimpleStringProperty currentChatId) {
        this.currentChatId = currentChatId;

        this.messagesModel = messagesModel;
        this.chatsModel = chatsModel;
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
                } else if (change.wasRemoved()) {
                    Platform.runLater(() -> messagesView.getMessagesVBox().getChildren().clear());
                }
            }
        });
    }

    /**
     * Process the message entered by the user.
     *
     * @param  None
     * @return None
     */
    private void processMessage() {
        String messageString = this.promptView.getPromptTextArea().getText().trim();
        if (messageString.isEmpty()) {
            return;
        }

        if (currentChatId.get() == null) {
            ChatApiService.addChat(chatsModel::addChat, currentChatId::set);
        }

        this.promptView.getPromptTextArea().clear();
        this.messagesModel.addMessage(messageString);
        ChatApiService.sendMessage(messagesModel::addMessage, currentChatId.get(), messageString);
    }

    /**
     * Loads messages for a given chat ID.
     *
     * @param chatId the ID of the chat for which to load messages
     */
    public void loadMessages(String chatId) {
        ChatApiService.getMessages(messagesModel::addMessage, chatId);
    }
}
