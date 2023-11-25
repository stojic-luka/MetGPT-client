package app.view;

import java.time.Month;
import java.util.Calendar;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CalendarTabView extends Tab {

    private Calendar c = null;
    private final GridPane grid = new GridPane();
    private final Label dateLabel = new Label();

    public CalendarTabView() {
        super();
        setText("Calendar");

        c = Calendar.getInstance();
        c.set(Calendar.DATE, 1);

        Button leftButton = new Button("<-");
        leftButton.setOnAction(e -> {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(150), grid);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();

            fadeOut.setOnFinished(ev -> {
                previousMonth();
                updateGridCalendar();

                FadeTransition fadeIn = new FadeTransition(Duration.millis(150), grid);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });
        });
        Button rightButton = new Button("->");
        rightButton.setOnAction(e -> {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(150), grid);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();

            fadeOut.setOnFinished(ev -> {
                nextMonth();
                updateGridCalendar();

                FadeTransition fadeIn = new FadeTransition(Duration.millis(150), grid);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });
        });

        updateGridCalendar();
        this.setContent(
                new VBox(
                        new HBox(leftButton, dateLabel, rightButton),
                        grid
                )
        );
    }

    private void updateGridCalendar() {
        grid.getChildren().clear();

        dateLabel.setText(String.format("%s, %d", Month.of(c.get(Calendar.MONTH) + 1), c.get(Calendar.YEAR)));

        int dayOfWeek = (c.get(Calendar.DAY_OF_WEEK) + 5) % 7;
        int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int cols = (int) Math.ceil((dayOfWeek - 1 + (double) daysInMonth) / 7);

        Calendar c1 = Calendar.getInstance();
        c1.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) - 1, 1);

        int startSkip = dayOfWeek;
        int prevMonthDayNum = c1.getActualMaximum(Calendar.DAY_OF_MONTH) - startSkip;

        for (int i = 0; i < startSkip; i++) {
            grid.add(new Button(String.valueOf(prevMonthDayNum + i + 1)), i, 0);
        }
        int dayCounter = 1, count = 1;
        for (int i = 0; i < cols; i++) {
            for (int j = 0 + startSkip; j < 7; j++) {
                if (dayCounter <= daysInMonth) {
                    grid.add(new Button(String.valueOf(dayCounter)), j, i);
                } else {
                    grid.add(new Button(String.valueOf(count)), j, i);
                    count++;
                }
                dayCounter++;
            }
            startSkip = 0;
        }
    }

    private void previousMonth() {
        c.add(Calendar.MONTH, -1);
    }

    private void nextMonth() {
        c.add(Calendar.MONTH, 1);
    }
}
