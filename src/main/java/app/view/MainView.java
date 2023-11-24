package app.view;

import javafx.scene.control.TabPane;

public class MainView extends TabPane {

    public MainView() {
        this.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        this.getTabs().addAll(new ChatView(),
                new CalendarView(),
                new NotepadView()
        );
    }
}
