package app.view.components.chat;

import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ChatView extends BorderPane {
    
    private final Label chatTitle;
    private final TextField renameChatField;
    private final Button chatOptionsButton;
    
    private final ContextMenu contextMenu;
    private final MenuItem renameItem, deleteItem;
    
    public ChatView(String title) {
        chatTitle = new Label(title);
        renameChatField = new TextField();
        
        contextMenu = new ContextMenu();
        renameItem = new MenuItem("Rename");
        deleteItem = new MenuItem("Delete");
        contextMenu.getItems().addAll(renameItem, deleteItem);
        
        chatOptionsButton = new Button("...");
        chatOptionsButton.setOnAction(e -> {
            contextMenu.show(chatOptionsButton, 0, chatOptionsButton.getHeight());
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
    
    public Button getChatOptionsButton() {
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
}
