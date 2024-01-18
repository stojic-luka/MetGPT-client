package app.view.components.chat;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PromptView extends BorderPane {

    private final TextArea promptTextArea;
    private final Button sendButton;

    public PromptView() {
        super();

        promptTextArea = new TextArea();
        promptTextArea.setId("textArea");
        promptTextArea.setWrapText(true);
        promptTextArea.setPromptText("Send a message");
        promptTextArea.setPrefSize(400, 50);
        promptTextArea.setMaxSize(400, 50);
        promptTextArea.setPadding(new Insets(2, 0, 0, 0));

        sendButton = new Button();
        sendButton.setPrefSize(40, 40);
        sendButton.setMaxSize(40, 40);
        sendButton.setStyle(
                "-fx-background-color: #2b2c36;"
                + "-fx-background-radius: 15px;"
                + "-fx-text-fill: white;"
                + "-fx-border-color: #1b1c28;"
                + "-fx-border-width: 1px;"
                + "-fx-font-size: 19px;"
                + "-fx-shape: \"M248 8C111 8 0 119 0 256s111 248 248 248 248-111 248-248S385 8 248 8zm115 168.7c-3.7 39.2-19.9 134.4-28.1 178.3-3.5 18.6-10.3 24.8-16.9 25.4-14.4 1.3-25.3-9.5-39.3-18.7-21.8-14.3-34.2-23.2-55.3-37.2-24.5-16.1-8.6-25 5.3-39.5 3.7-3.8 67.1-61.5 68.3-66.7.2-.7.3-3.1-1.2-4.4s-3.6-.8-5.1-.5q-3.3.7-104.6 69.1-14.8 10.2-26.9 9.9c-8.9-.2-25.9-5-38.6-9.1-15.5-5-27.9-7.7-26.8-16.3q.8-6.7 18.5-13.7 108.4-47.2 144.6-62.3c68.9-28.6 83.2-33.6 92.5-33.8 2.1 0 6.6.5 9.6 2.9a10.5 10.5 0 0 1 3.5 6.7 43.8 43.8 0 0 1 .5 9.9z\""
        );
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
        prompt.add(promptTextArea, 0, 0);
        prompt.add(sendButtonPane, 1, 0);
        BorderPane promptPane = new BorderPane();
        promptPane.setCenter(prompt);

        this.setMinHeight(80);
        this.setCenter(prompt);
        this.setStyle("-fx-background-color: #353640;");
    }

    public TextArea getPromptTextArea() {
        return promptTextArea;
    }

    public Button getSendButton() {
        return sendButton;
    }
}
