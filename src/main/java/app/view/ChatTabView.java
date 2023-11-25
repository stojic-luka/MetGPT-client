package app.view;

import app.view.components.chat.MessagesView;
import app.view.components.chat.SidePaneView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class ChatTabView extends Tab {

    private final MessagesView messagesView;
    private final SidePaneView sidePaneView;

    public ChatTabView() {
        super();
        this.setText("ChatBot");

        messagesView = new MessagesView();
        AnchorPane.setTopAnchor(messagesView, 0.0);
        AnchorPane.setRightAnchor(messagesView, 0.0);
        AnchorPane.setBottomAnchor(messagesView, 0.0);
        AnchorPane.setLeftAnchor(messagesView, 0.0);

        sidePaneView = new SidePaneView();
        AnchorPane.setTopAnchor(sidePaneView, 0.0);
        AnchorPane.setBottomAnchor(sidePaneView, 0.0);

        this.setContent(new AnchorPane(messagesView, sidePaneView));
    }

    public MessagesView getMessagesView() {
        return messagesView;
    }

    public SidePaneView getSidePaneView() {
        return sidePaneView;
    }
}
