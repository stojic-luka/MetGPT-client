package app.view;

import app.util.NetworkManager;
import app.view.components.chat.MessagesController;
import app.view.components.chat.SidePaneView;
import app.view.components.chat.UserMessage;
import com.google.gson.JsonObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChatView extends Tab {

    private final VBox messages;

    public ChatView() {
        this.setText("ChatBot");

        BorderPane chat = new BorderPane();
        chat.setStyle("-fx-background-color: #444654;");

        TextArea textArea = new TextArea();
        textArea.setId("textArea");
        textArea.setWrapText(true);
        textArea.setPromptText("Send a message");
        textArea.setPrefSize(400, 50);
        textArea.setMaxSize(400, 50);
        textArea.setPadding(new Insets(2, 0, 0, 0));
        textArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && !event.isShiftDown()) {
                event.consume();
                String msg = textArea.getText().trim();
                textArea.clear();
                processMessage(msg);
            } else if (event.getCode() == KeyCode.ENTER && event.isShiftDown()) {
                textArea.appendText("\n");
            }

            if (textArea.getText().length() > 200) {
                textArea.setText(textArea.getText().substring(0, 200));
            }
        });

        Button sendButton = new Button();
        sendButton.setPrefSize(40, 40);
        sendButton.setMaxSize(40, 40);
        sendButton.setOnAction(event -> {
            String msg = textArea.getText().trim();
            textArea.clear();
            processMessage(msg);
        });
        VBox sendButtonPane = new VBox(sendButton);
        sendButtonPane.setPrefSize(40, 40);
        sendButtonPane.setMaxSize(40, 40);
        VBox.setMargin(sendButton, new Insets(2, 0, 0, 0));

        GridPane prompt = new GridPane();
        prompt.setId("prompt");
        prompt.setMaxSize(447, 54);
        DropShadow promptShadow = new DropShadow(
                BlurType.GAUSSIAN,
                Color.rgb(43, 44, 52),
                10, 10, 0, 0);
        promptShadow.setSpread(0.5);
        prompt.setEffect(promptShadow);
        prompt.add(textArea, 0, 0);
        prompt.add(sendButtonPane, 1, 0);
        BorderPane promptPane = new BorderPane();
        promptPane.setCenter(prompt);

        BorderPane chatPane = new BorderPane();
        chatPane.setMinHeight(80);
        chatPane.setCenter(prompt);
        chatPane.setStyle("-fx-background-color: #353640;");

        messages = new VBox();
        messages.setSpacing(10);
        messages.setPadding(new Insets(0, 0, 10, 0));
        messages.setMaxHeight(0);
        messages.setMaxWidth(600);
        messages.setStyle("-fx-background-color: #444654;");

        StackPane stack = new StackPane(messages);
        stack.setAlignment(Pos.BOTTOM_CENTER);
        ScrollPane messagesScroll = new ScrollPane();
        messagesScroll.setId("messagesScroll");
        messagesScroll.setContent(stack);
        messagesScroll.setFitToWidth(true);
        messagesScroll.setFitToHeight(true);

        chat.setBottom(chatPane);
        chat.setCenter(messagesScroll);
        AnchorPane.setTopAnchor(chat, 0.0);
        AnchorPane.setRightAnchor(chat, 0.0);
        AnchorPane.setBottomAnchor(chat, 0.0);
        AnchorPane.setLeftAnchor(chat, 0.0);

        this.setContent(new AnchorPane(chat, new SidePaneView()));
    }

    private void processMessage(String message) {
        if (message.isEmpty()) {
            return;
        }

        addUserMessage(message);
        JsonObject objMessage = new JsonObject();
        objMessage.addProperty("input", message);
        MessagesController botMessage = new MessagesController();
        NetworkManager.sendPostRequestAsync("http://127.0.0.1:8080/api/v1/chat", objMessage, response -> {
            String botMsg = response.get("message").getAsString();
            botMessage.setMessage(botMsg);
        });
    }
    
    private void addUserMessage(String msg) {
        messages.getChildren().add(new UserMessage(msg));
    }
    
    private void loadMessages() {
        
    }
    
    private void loadChats() {
        
    }
}
