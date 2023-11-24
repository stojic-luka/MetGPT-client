package app.model.notepad;

public class Note {

    private String title;
    private String note;

    public Note() {
        title = "";
        note = "";
    }
    
    public Note(String title, String note) {
        this.title = title;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean contains(String str) {
        return title.contains(str) || note.contains(str);
    }

    @Override
    public String toString() {
        return "Note{" + "title=" + title + ", note=" + note + '}';
    }
}
