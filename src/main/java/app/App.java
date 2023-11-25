package app;

import app.controller.CalendarController;
import app.controller.ChatController;
import app.controller.NotepadController;
import app.view.CalendarTabView;
import app.view.ChatTabView;
import app.view.NotepadTabView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        ChatTabView chatView = new ChatTabView();
        CalendarTabView calendarView = new CalendarTabView();
        NotepadTabView notepadView = new NotepadTabView();

        var chatController = new ChatController(chatView);
        var calendarController = new CalendarController(calendarView);
        var notepadController = new NotepadController(notepadView);

        TabPane root = new TabPane();
        root.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        root.getTabs().addAll(
                chatView,
                calendarView,
                notepadView
        );

        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/app.css").toExternalForm());
        stage.setTitle("ChatBot");
        stage.setMinHeight(375);
        stage.setMinWidth(480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
