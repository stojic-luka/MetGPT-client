package app.controller.components.notepad;

import app.model.notepad.Note;
import app.model.notepad.NotesModel;
import app.view.components.notepad.NoteDialog;

public class NoteDialogController {

    private Note note;
    private final NoteDialog dialog;
//    private final NotesModel notesModel;

    public NoteDialogController(NotesModel notesModel) {
        this.note = new Note();
        this.dialog = new NoteDialog();
//        this.notesModel = notesModel;

        dialog.getTitleTextField().textProperty().addListener((obs, oldVal, newVal) -> note.setTitle(newVal));
        dialog.getNoteTextArea().textProperty().addListener((obs, oldVal, newVal) -> note.setNote(newVal));

        dialog.getSaveButton().setOnAction(e -> {
            notesModel.addNote(note);
            dialog.closeDialog();
            note.clear();
        });
        dialog.getCancelButton().setOnAction(e -> {
            dialog.closeDialog();
            note.clear();
        });
    }

    public NoteDialog getEditDialog(Note note) {
        return getEditDialog(note.getTitle(), note.getNote());
    }

    public NoteDialog getEditDialog(String title, String note) {
        dialog.setTitle("Edit note");
        dialog.getTitleTextField().setText(title);
        dialog.getNoteTextArea().setText(note);
        return dialog;
    }

    public NoteDialog getAddDialog() {
        dialog.setTitle("Add note");
        return dialog;
    }
}
