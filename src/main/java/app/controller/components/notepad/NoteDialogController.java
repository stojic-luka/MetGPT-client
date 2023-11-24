package app.controller.components.notepad;

import app.model.notepad.Note;
import app.view.components.notepad.NoteDialog;

public class NoteDialogController {

    private Note note;
    private final NoteDialog dialog = new NoteDialog();
    
    public NoteDialogController() {
        dialog.getTitleTextField().textProperty().addListener((obs, oldVal, newVal) -> note.setTitle(newVal));
        dialog.getNoteTextArea().textProperty().addListener((obs, oldVal, newVal) -> note.setNote(newVal));
        
        dialog.getSaveButton().setOnAction(e -> {
            
        });
        dialog.getCancelButton().setOnAction(e -> {
        
        });
    }
}
