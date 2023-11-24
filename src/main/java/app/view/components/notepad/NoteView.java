package app.view.components.notepad;

import javafx.beans.property.SimpleStringProperty;
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

    public NoteView(String title, String note) {
        Label titleLabel = new Label(title);
        Label noteLabel = new Label(note);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem editItem = new MenuItem("Edit");
        editItem.setOnAction(e -> {
            NoteView parentNote = (NoteView) titleLabel.getParent().getUserData();
            String titleEdit = parentNote.getTitle();
            String noteEdit = parentNote.getNote();

            titleTextField.setText(titleEdit);
            noteTextArea.setText(noteEdit);

            addNoteDialog.show();
            notesVBox.getChildren().remove(titleLabel.getParent());
        });
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(e -> {
            notesVBox.getChildren().remove(titleLabel.getParent());
        });
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
        vBox.setUserData(new NoteView(title, note));
        vBox.setOnContextMenuRequested(e -> {
            contextMenu.show(vBox, e.getScreenX(), e.getScreenY());
        });

        this.getChildren().add(vBox);
    }
}
