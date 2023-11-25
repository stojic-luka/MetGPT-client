package app.model.notepad;

import javafx.scene.Node;

public class Note {

    private String title;
    private String content;

    private Node parent;

    public Note() {
        title = "";
        content = "";
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note(String title, String content, Node parent) {
        this.title = title;
        this.content = content;
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void clear() {
        this.title = "";
        this.content = "";
    }

    public boolean contains(String str) {
        return title.contains(str) || content.contains(str);
    }

    @Override
    public String toString() {
        return "Note{" + "title=" + title + ", content=" + content + '}';
    }
}
