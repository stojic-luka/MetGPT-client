package app.model.calendar;

import java.time.LocalDateTime;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class RemindersModel {

    private final ObservableMap<String, SimpleListProperty<Reminder>> reminderList = FXCollections.observableHashMap();
    private final SimpleMapProperty<String, SimpleListProperty<Reminder>> reminderListProperty = new SimpleMapProperty<>(reminderList);

    public ObservableMap<String, SimpleListProperty<Reminder>> getReminderList() {
        return reminderListProperty.get();
    }

    public SimpleMapProperty<String, SimpleListProperty<Reminder>> getReminderListProperty() {
        return reminderListProperty;
    }

    public ObservableList<Reminder> getReminder(String key) {
        return reminderListProperty.get().get(key).get();
    }

    public SimpleListProperty<Reminder> getReminderProperty(String key) {
        return reminderListProperty.get().get(key);
    }

    private String formatKey(LocalDateTime assignedTime) {
        return String.format("%d-%02d", assignedTime.getYear(), assignedTime.getMonthValue());
    }

    public void addReminder(String content, LocalDateTime assignedTime) {
        String key = formatKey(assignedTime);
        if (reminderList.containsKey(key)) {
            this.getReminder(key).add(new Reminder(content, assignedTime));
        } else {
            ObservableList<Reminder> chats = FXCollections.observableArrayList();
            chats.add(new Reminder(content, assignedTime));
            reminderList.put(key, new SimpleListProperty<>(chats));
        }
    }
}
