package app.view.components.notepad;

import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NoteView extends StackPane {

    private final ContextMenu contextMenu;
    private final MenuItem deleteItem;
    private final MenuItem editItem;

    public NoteView(String title, String note) {
        super();
        Label titleLabel = new Label(title);
        Label noteLabel = new Label(note);

        contextMenu = new ContextMenu();
        editItem = new MenuItem("Edit");
        deleteItem = new MenuItem("Delete");
        contextMenu.getItems().addAll(editItem, deleteItem);

        VBox vBox = new VBox(titleLabel, noteLabel);
        vBox.setMaxWidth(450);
        vBox.setPadding(new Insets(5, 10, 5, 10));
        vBox.getStyleClass().add("note");
        DropShadow promptShadow = new DropShadow(
                BlurType.GAUSSIAN,
                Color.rgb(43, 44, 52, 0.5),
                20, 10, 0, 0);
        promptShadow.setSpread(0.4);
        vBox.setEffect(promptShadow);
        this.setOnContextMenuRequested(e -> contextMenu.show(vBox, e.getScreenX(), e.getScreenY()));

        this.getChildren().add(vBox);
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public MenuItem getEditItem() {
        return editItem;
    }

    public MenuItem getDeleteItem() {
        return deleteItem;
    }
}
