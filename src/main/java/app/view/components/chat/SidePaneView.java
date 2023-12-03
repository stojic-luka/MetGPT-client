package app.view.components.chat;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SidePaneView extends HBox {

    private boolean isSidePanelShowing = false;

    private final VBox chatsVBox;
    private final Button addChatButton;
    
    public SidePaneView() {
        super();
        
        addChatButton = new Button("New Chat");
        addChatButton.setAlignment(Pos.CENTER_LEFT);
        addChatButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(addChatButton, new Insets(5));

        chatsVBox = new VBox();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(chatsVBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        
        VBox vBox = new VBox(addChatButton, scrollPane);
        vBox.setSpacing(5);
        vBox.setMinWidth(250);
        vBox.setMaxWidth(250);
        vBox.setMaxHeight(Double.MAX_VALUE);
        vBox.toFront();

        Button toggleButton = new Button();
        toggleButton.setPrefSize(40, 40);
        HBox.setMargin(toggleButton, new Insets(10));

        this.getChildren().addAll(vBox, toggleButton);

        TranslateTransition hideSidePanelTransition = new TranslateTransition(Duration.millis(250), this);
        hideSidePanelTransition.setInterpolator(Interpolator.EASE_BOTH);
        hideSidePanelTransition.setToX(-250);
        TranslateTransition showSidePanelTransition = new TranslateTransition(Duration.millis(250), this);
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
    
    public VBox getChatsVBox() {
        return chatsVBox;
    }

    public Button getAddChatButton() {
        return addChatButton;
    }
}
