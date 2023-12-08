package app.controller.components.notepad;

import app.model.notepad.Note;
import app.model.notepad.NotesModel;
import app.view.components.notepad.NoteDialog;

public class NoteDialogController {

    private Note note;
    private final NoteDialog dialog;
    private final NotesModel notesModel;

    public NoteDialogController(NotesModel notesModel) {
        this.note = new Note();
        this.dialog = new NoteDialog();
        this.notesModel = notesModel;

        dialog.getTitleTextField().textProperty().addListener((obs, oldVal, newVal) -> note.setTitle(newVal));
        dialog.getNoteTextArea().textProperty().addListener((obs, oldVal, newVal) -> note.setContent(newVal));

        dialog.getCancelButton().setOnAction(e -> {
            dialog.closeDialog();
            note.clear();
        });
    }

    public NoteDialog getEditDialog(Note note) {
        return getEditDialog(note.getTitle(), note.getContent());
    }

    public NoteDialog getEditDialog(String title, String content) {
        dialog.setTitle("Edit note");
        dialog.getTitleTextField().setText(title);
        dialog.getNoteTextArea().setText(content);

        dialog.getSaveButton().setOnAction(e -> {
            notesModel.addNote(note);
            dialog.closeDialog();
            note.clear();
        });

        return dialog;
    }

    public NoteDialog getAddDialog() {
        dialog.setTitle("Add note");

        dialog.getSaveButton().setOnAction(e -> {
            notesModel.addNote(note);
            dialog.closeDialog();
            note.clear();
        });

        return dialog;
    }
}
