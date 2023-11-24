package app.view;

import app.view.components.chat.MessagesView;
import app.view.components.chat.SidePaneView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class ChatView extends Tab {

    public ChatView() {
        this.setText("ChatBot");

        MessagesView messagesView = new MessagesView();
        AnchorPane.setTopAnchor(messagesView, 0.0);
        AnchorPane.setRightAnchor(messagesView, 0.0);
        AnchorPane.setBottomAnchor(messagesView, 0.0);
        AnchorPane.setLeftAnchor(messagesView, 0.0);
        this.setContent(new AnchorPane(messagesView, new SidePaneView()));
    }
}
