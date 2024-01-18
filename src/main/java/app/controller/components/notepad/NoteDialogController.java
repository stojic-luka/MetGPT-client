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

    /**
     * Retrieves the edit dialog for a given note.
     *
     * @param  note   the note to be edited
     * @return        the edit dialog for the note
     */
    public NoteDialog getEditDialog(Note note) {
        return getEditDialog(note.getTitle(), note.getContent());
    }

    /**
     * Retrieves the edit dialog for a note with the specified title and content.
     *
     * @param  title   the title of the note
     * @param  content the content of the note
     * @return         the NoteDialog object representing the edit dialog
     */
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

    /**
     * Returns the NoteDialog object used for adding a new note.
     *
     * @return         	The NoteDialog object for adding a new note.
     */
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
