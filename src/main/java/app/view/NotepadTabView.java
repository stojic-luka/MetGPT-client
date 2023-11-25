package app.view;

import javafx.geometry.Insets;
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

public class NotepadTabView extends Tab {

    private final VBox notesVBox;

    private final Button addNoteButton;
    private final TextField searchTextField;

    public NotepadTabView() {
        super();
        this.setText("NotePad");

        notesVBox = new VBox();
        ScrollPane scrollPane = new ScrollPane(notesVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("notes");
        scrollPane.setPadding(new Insets(10));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        borderPane.setStyle("-fx-background-color: #353640;");

        searchTextField = new TextField();
        DropShadow promptShadow = new DropShadow(
                BlurType.GAUSSIAN,
                Color.rgb(43, 44, 52),
                10, 10, 0, 0);
        promptShadow.setSpread(0.5);
        searchTextField.setEffect(promptShadow);
        searchTextField.getStyleClass().add("text-field-custom");

        addNoteButton = new Button("+");

        HBox topHBox = new HBox(
                searchTextField,
                addNoteButton);
        topHBox.setPadding(new Insets(7));
        topHBox.setSpacing(5);
        HBox.setHgrow(searchTextField, Priority.ALWAYS);

        borderPane.setTop(topHBox);

        this.setContent(borderPane);
    }

    public VBox getNotesVBox() {
        return notesVBox;
    }

    public Button getAddNoteButton() {
        return addNoteButton;
    }

    public TextField getSearchTextField() {
        return searchTextField;
    }
}
