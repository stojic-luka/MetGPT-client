package app.view.components.notepad;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NoteDialog extends Stage {
    
    private final TextField titleTextField = new TextField();
    private final TextArea noteTextArea = new TextArea();
    
    private final Button saveButton, cancelButton;
    
    public NoteDialog() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        
        HBox titleHBox = new HBox(
                new Label("Title:"),
                titleTextField
        );
        
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
        
        this.setScene(
                new Scene(
                        new VBox(
                                titleHBox,
                                noteTextArea,
                                new HBox(cancelButton, saveButton)
                        ),
                        300, 200
                )
        );
        
        this.setOnCloseRequest(e -> closeDialog());
    }
    
    public TextField getTitleTextField() {
        return titleTextField;
    }
    
    public TextArea getNoteTextArea() {
        return noteTextArea;
    }
    
    public Button getSaveButton() {
        return saveButton;
    }
    
    public Button getCancelButton() {
        return cancelButton;
    }
    
    public void showDialog() {
        this.show();
    }
    
    public void closeDialog() {
        this.hide();
        titleTextField.clear();
        noteTextArea.clear();
    }
}
