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
}
