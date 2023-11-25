package app.controller;

import app.controller.components.notepad.NoteDialogController;
import app.model.notepad.Note;
import app.model.notepad.NotesModel;
import app.view.NotepadView;
import app.view.components.notepad.NoteView;
import javafx.collections.ListChangeListener;

public class NotepadController {

    private final NotesModel notesModel;
    private final NotepadView notepadView;
    private final NoteDialogController noteDialogController;

    public NotepadController(NotepadView notepadView) {
        this.notesModel = new NotesModel();
        this.notepadView = notepadView;

        noteDialogController = new NoteDialogController(notesModel);

        notesModel.getNotes().addListener((ListChangeListener<Note>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Note note : change.getAddedSubList()) {
                        NoteView noteView = new NoteView(note.getTitle(), note.getNote());
//                        noteView.setUserData(note);
                        notepadView.getNotesVBox().getChildren().add(noteView);
                    }
                }
            }
        });
        
        

        notepadView.getAddNoteButton().setOnAction(e -> noteDialogController.getAddDialog().showDialog());

//        notepadView.getSearchTextField()
    }
}
