package app.view.components.chat;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class SidePaneView extends HBox {

    private boolean isSidePanelShowing = false;

    private final VBox chatsVBox;
    private final Button addChatButton;

    public SidePaneView() {
        super();
        this.getStylesheets().add(getClass().getClassLoader().getResource("styles/chat/style.css").toExternalForm());

        addChatButton = new Button("New Chat");
        addChatButton.setAlignment(Pos.CENTER_LEFT);
        addChatButton.setMaxWidth(Double.MAX_VALUE);
        addChatButton.setStyle(
                "-fx-background-color: #444654;"
                + "-fx-background-radius: 5px;"
                + "-fx-text-fill: white;"
                + "-fx-border-color: #1b1c28;"
                + "-fx-border-width: 1px;"
                + "-fx-border-radius: 5px;"
                + "-fx-font-weight: bold;"
        );
        VBox.setMargin(addChatButton, new Insets(5));

        chatsVBox = new VBox();
        chatsVBox.setSpacing(2);
        chatsVBox.setStyle(
                "-fx-background-color: #2f303c;"
//                + "-fx-border-width: 0;"
//                + "-fx-box-border: transparent;"
        );

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(chatsVBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: #2f303c;");

        VBox vBox = new VBox(addChatButton, scrollPane);
        vBox.setSpacing(5);
        vBox.setMinWidth(250);
        vBox.setMaxWidth(250);
        vBox.setMaxHeight(Double.MAX_VALUE);
        vBox.setStyle(
                "-fx-background-color: #2f303c;"
        );
        vBox.setBorder(
                new Border(
                        new BorderStroke(
                                Color.RED, Color.rgb(37, 38, 50), Color.RED, Color.RED,
                                BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
                                CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY
                        )
                )
        );
        vBox.toFront();

        SVGPath svgPath = new SVGPath();
        svgPath.setContent("M4 18h6M4 12h12M4 6h16");
        svgPath.setStroke(Color.WHITE);
        svgPath.setStrokeWidth(2);
        svgPath.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);

        Button toggleButton = new Button("", svgPath);
        toggleButton.setPrefSize(40, 40);
        toggleButton.setStyle(
                "-fx-background-color: #2b2c36;"
                + "-fx-background-radius: 20px;"
                + "-fx-text-fill: white;"
                + "-fx-border-color: #1b1c28;"
                + "-fx-border-width: 1px;"
                + "-fx-border-radius: 20px;"
                + "-fx-font-size: 19px;"
        );
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
