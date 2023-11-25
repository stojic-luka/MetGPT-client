package app.model.notepad;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NotesModel {

    private final ObservableList<Note> notes = FXCollections.observableArrayList();
    private final SimpleListProperty<Note> notesProperty = new SimpleListProperty<>(notes);

    public ObservableList<Note> getNotes() {
        return notesProperty.get();
    }

    public SimpleListProperty<Note> getNotesProperty() {
        return notesProperty;
    }

    public void addNote(String title, String note) {
        notes.add(new Note(title, note));
    }

    public void addNote(Note note) {
        notes.add(note);
    }
    
    public void removeNote(Note note) {
        notes.remove(note);
    }

    @Override
    public String toString() {
        return "NotesModel{" + "notes=" + notes + '}';
    }
}
