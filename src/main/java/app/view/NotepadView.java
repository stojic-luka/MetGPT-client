package app.view;

import app.view.components.notepad.NoteView;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NotepadView extends Tab {

    private final VBox notesVBox = new VBox();

    public NotepadView() {
        this.setText("NotePad");

        try {
            //            loadNotes();
        } catch (Exception e) {
            Alert a = new Alert(AlertType.WARNING);
            a.show();
        }

        BorderPane borderPane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane(notesVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("notes");
        scrollPane.setPadding(new Insets(10));
        borderPane.setCenter(scrollPane);
        borderPane.setStyle("-fx-background-color: #353640;");

        TextField searchBox = new TextField();
        DropShadow promptShadow = new DropShadow(
                BlurType.GAUSSIAN,
                Color.rgb(43, 44, 52),
                10, 10, 0, 0);
        promptShadow.setSpread(0.5);
        searchBox.setEffect(promptShadow);
        searchBox.getStyleClass().add("text-field-custom");

        Button addNote = new Button("+");
        addNote.setOnAction((e) -> {
            addNoteDialog.show();
        });

        HBox topHBox = new HBox(
                searchBox,
                addNote);
        topHBox.setPadding(new Insets(7));
        topHBox.setSpacing(5);
        HBox.setHgrow(searchBox, Priority.ALWAYS);

        borderPane.setTop(topHBox);

        this.setContent(borderPane);
    }

    private void addNote(String title, String note) {
        notesVBox.getChildren().add(new NoteView(title, note));
    }

    private void loadNotes() {
    }
}
