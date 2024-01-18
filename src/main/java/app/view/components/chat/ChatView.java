package app.view.components.chat;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class ChatView extends BorderPane {

    private final Label chatTitle;
    private final TextField renameChatField;
    private final StackPane chatOptionsButton;

    private final ContextMenu contextMenu;
    private final MenuItem renameItem, deleteItem;

    public ChatView(String title) {
        super();
        this.setPrefHeight(30);
        this.setStyle(
                "-fx-background-radius: 5px;"
                + "-fx-border-radius: 5px;"
        );

        chatTitle = new Label(title);
        chatTitle.setTextFill(Color.WHITE);
        renameChatField = new TextField();

        contextMenu = new ContextMenu();
        renameItem = new MenuItem("Rename");
        deleteItem = new MenuItem("Delete");
        contextMenu.getItems().addAll(renameItem, deleteItem);

        SVGPath svgPath = new SVGPath();
        svgPath.setContent("M10.088 12.06a3.1 3.1 0 0 0-3.101 3.1 3.1 3.1 0 0 0 3.101 3.101 3.1 3.1 0 1 0 0-6.2zm9.587 0a3.1 3.1 0 0 0-3.102 3.1c0 1.713 1.39 3.1 3.102 3.1a3.101 3.101 0 0 0 3.103-3.1 3.103 3.103 0 0 0-3.103-3.1zm9.267 0a3.1 3.1 0 1 0-.004 6.201 3.1 3.1 0 0 0 .004-6.2z");
        svgPath.setFill(Color.WHITE);

        chatOptionsButton = new StackPane(svgPath);
        chatOptionsButton.setPrefWidth(40);
        chatOptionsButton.setStyle(
                "-fx-border-color: transparent;"
                + "-fx-border-width: 0;"
                + "-fx-background-radius: 0;"
                + "-fx-background-color: transparent;"
        );
        chatOptionsButton.setOnMouseClicked(e -> {
            Stage stage = (Stage) chatTitle.getScene().getWindow();
            double xPos = stage.getX() + chatOptionsButton.localToScene(0, 0).getX() + chatOptionsButton.getWidth();
            double yPos = stage.getY() + chatOptionsButton.localToScene(0, 0).getY() + chatOptionsButton.getHeight();
            contextMenu.show(chatOptionsButton, xPos, yPos);
        });

        this.setCenter(chatTitle);
        this.setRight(chatOptionsButton);
    }

    public TextField getRenameChatField() {
        return renameChatField;
    }

    public Label getChatTitle() {
        return chatTitle;
    }

    public StackPane getChatOptionsButton() {
        return chatOptionsButton;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public MenuItem getRenameItem() {
        return renameItem;
    }

    public MenuItem getDeleteItem() {
        return deleteItem;
    }

    public void toggleRename() {
        if (this.getCenter() instanceof Label) {
            this.setCenter(renameChatField);
        } else {
            this.setCenter(chatTitle);
        }
    }

    public void isSelected(boolean selected) {
        if (selected) {
            this.getStyleClass().add("chat-selected");
        } else {
            this.getStyleClass().remove("chat-selected");
        }
    }
}
