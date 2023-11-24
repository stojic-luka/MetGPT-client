package app.view.components.chat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MessagesView extends BorderPane {

    private final VBox messages;

    public MessagesView() {
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

        this.setBottom(new PromptView());
        this.setCenter(messagesScroll);
        this.setStyle("-fx-background-color: #444654;");
    }
    
    
}
