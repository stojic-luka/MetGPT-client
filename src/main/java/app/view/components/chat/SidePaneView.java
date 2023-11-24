package app.view.components.chat;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SidePaneView extends HBox {

    private boolean isSidePanelShowing = false;

    private VBox chats;

    public SidePaneView() {
        super();
        
        Button addChatButton = new Button("New Chat");
        addChatButton.setAlignment(Pos.CENTER_LEFT);
        addChatButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(addChatButton, new Insets(5));
        addChatButton.setOnAction(e -> {

        });

        chats = new VBox(new Button("chat 1"), new Button("chat 2"), new Button("chat 3"));

        VBox vBox = new VBox(addChatButton, chats);
        vBox.setSpacing(5);
        vBox.setMinWidth(250);
        vBox.setMaxWidth(250);
        vBox.setMaxHeight(Double.MAX_VALUE);
        vBox.toFront();

        Button toggleButton = new Button();
        toggleButton.setPrefSize(40, 40);
        HBox.setMargin(toggleButton, new Insets(10));

        HBox hBox = new HBox(vBox, toggleButton);
        AnchorPane.setTopAnchor(hBox, 0.0);
        AnchorPane.setBottomAnchor(hBox, 0.0);

        TranslateTransition hideSidePanelTransition = new TranslateTransition(Duration.millis(250), hBox);
        hideSidePanelTransition.setInterpolator(Interpolator.EASE_BOTH);
        hideSidePanelTransition.setToX(-250);
        TranslateTransition showSidePanelTransition = new TranslateTransition(Duration.millis(250), hBox);
        showSidePanelTransition.setInterpolator(Interpolator.EASE_BOTH);
        showSidePanelTransition.setToX(0);

        toggleButton.setOnAction(e -> {
            if (isSidePanelShowing) {
                showSidePanelTransition.play();
            } else {
                hideSidePanelTransition.play();
            }
            isSidePanelShowing = !isSidePanelShowing;
        });
    }
    
    private void loadChats() {
        
    }
}
