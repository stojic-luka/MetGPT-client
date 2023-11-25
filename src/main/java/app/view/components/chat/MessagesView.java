package app.view.components.chat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MessagesView extends BorderPane {

    private final VBox messagesVBox;
    private final PromptView promptView;

    public MessagesView() {
        super();
        
        messagesVBox = new VBox();
        messagesVBox.setSpacing(10);
        messagesVBox.setPadding(new Insets(0, 0, 10, 0));
        messagesVBox.setMaxHeight(0);
        messagesVBox.setMaxWidth(600);
        messagesVBox.setStyle("-fx-background-color: #444654;");

        StackPane stack = new StackPane(messagesVBox);
        stack.setAlignment(Pos.BOTTOM_CENTER);
        ScrollPane messagesScroll = new ScrollPane();
        messagesScroll.setId("messagesScroll");
        messagesScroll.setContent(stack);
        messagesScroll.setFitToWidth(true);
        messagesScroll.setFitToHeight(true);

        promptView = new PromptView();
        this.setBottom(promptView);
        this.setCenter(messagesScroll);
        this.setStyle("-fx-background-color: #444654;");
    }

    public VBox getMessagesVBox() {
        return messagesVBox;
    }

    public PromptView getPromptView() {
        return promptView;
    }
}
