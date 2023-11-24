package app;

import app.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new MainView(), 1000, 600);
        //        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/app.css").toExternalForm());
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
