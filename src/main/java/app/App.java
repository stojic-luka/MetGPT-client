package app;

import app.controller.CalendarTabController;
import app.controller.ChatTabController;
import app.controller.NotepadTabController;
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

        var chatController = new ChatTabController(chatView);
        var calendarController = new CalendarTabController(calendarView);
        var notepadController = new NotepadTabController(notepadView);

        TabPane root = new TabPane();
        root.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        root.getTabs().addAll(
                chatView,
                calendarView,
                notepadView
        );

        Scene scene = new Scene(root, 1000, 600);

        scene.getStylesheets().addAll(
//                getClass().getClassLoader().getResource("styles/chat/style.css").toExternalForm(),
                getClass().getClassLoader().getResource("styles/calendar/style.css").toExternalForm(),
                getClass().getClassLoader().getResource("styles/notepad/style.css").toExternalForm(),
                getClass().getClassLoader().getResource("styles/app.css").toExternalForm()
        );

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
