package app.view.components.chat;

import javafx.geometry.Insets;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UserMessage extends VBox {

    private final Color userTextFill = Color.rgb(236, 236, 241);

    private final Text senderName;
    private final BorderPane messageBubble;

    public UserMessage(String msg) {
        super();

        Text txt = new Text(msg);
        txt.setFill(userTextFill);

        senderName = new Text("You");
        senderName.setFill(userTextFill);
        senderName.getStyleClass().add("senderName");

        BorderPane message = new BorderPane();
        message.setCenter(txt);
        message.setMaxWidth(540);
        message.setId("message");
        messageBubble = new BorderPane();
        messageBubble.setPadding(new Insets(10));
        DropShadow promptShadow = new DropShadow(
                BlurType.GAUSSIAN,
                Color.rgb(43, 44, 52, 0.5),
                20, 10, 0, 0);
        promptShadow.setSpread(0.4);
        messageBubble.setEffect(promptShadow);
        messageBubble.setRight(message);
        messageBubble.getStyleClass().add("message-bubble");

        this.getChildren().addAll(senderName, messageBubble);
        this.setSpacing(5);
    }
}
