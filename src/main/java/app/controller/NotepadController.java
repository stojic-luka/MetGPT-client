package app.controller;

import app.controller.components.notepad.NoteDialogController;
import app.model.notepad.Note;
import app.model.notepad.NotesModel;
import app.view.NotepadTabView;
import app.view.components.notepad.NoteView;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class NotepadController {

    private final NotesModel notesModel;
    private final NotepadTabView notepadView;
    private final NoteDialogController noteDialogController;

    public NotepadController(NotepadTabView notepadView) {
        this.notesModel = new NotesModel();
        this.notepadView = notepadView;

        noteDialogController = new NoteDialogController(notesModel);

        final EventHandler<ActionEvent> editLambda = e -> {
            MenuItem menuItem = (MenuItem) e.getSource();
            Note note = (Note) menuItem.getParentPopup().getUserData();

            System.out.println(note);

            // TODO: fix edit button
            // moguce resenje prosledi lambdu koja se izvrsava na cancel, save ili exit
            notesModel.getNotes().remove(note);
            noteDialogController.getEditDialog(note
            //                    () -> {
            //                    }
            ).showDialog();
        };

        final EventHandler<ActionEvent> deleteLambda = e -> {
            MenuItem menuItem = (MenuItem) e.getSource();
            Note note = (Note) menuItem.getParentPopup().getUserData();

            notesModel.getNotes().remove(note);
        };

        notesModel.getNotes().addListener((ListChangeListener<Note>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Note note : change.getAddedSubList()) {
                        NoteView noteView = new NoteView(note.getTitle(), note.getContent());
                        noteView.getEditItem().setOnAction(editLambda);
                        noteView.getDeleteItem().setOnAction(deleteLambda);
                        noteView.getContextMenu().setUserData(note);
                        note.setParent(noteView);

                        notepadView.getNotesVBox().getChildren().add(noteView);
                    }
                } else if (change.wasRemoved()) {
                    for (Note note : change.getRemoved()) {
                        notepadView.getNotesVBox().getChildren().remove(note.getParent());
                    }
                }
            }
            System.out.println(notesModel.getNotes());
        });
        
        notepadView.getAddNoteButton().setOnAction(e -> noteDialogController.getAddDialog().showDialog());

//        notepadView.getSearchTextField()
    }
}
