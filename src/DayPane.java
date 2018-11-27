import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javafx.geometry.Pos.CENTER;

public class DayPane extends BorderPane {

    private final String MESSAGE;

    private VBox eventsBox; // todo: change to ScrollPane
    protected List<Event> events;

    DayPane(String day) {
        this.MESSAGE = day;

        Text dayText = new Text(MESSAGE);
        this.setTop(dayText);
        setAlignment(dayText, CENTER);

        eventsBox = new VBox();
        eventsBox.setAlignment(CENTER);
        this.setBottom(eventsBox);
        setAlignment(eventsBox, CENTER);

        events = new ArrayList<>();

        this.setPrefWidth(MonthPane.CELL_WIDTH);
        this.setPrefHeight(MonthPane.CELL_HEIGHT);
        this.setOnMouseClicked(event -> {
//                addEvent(new Event("hello"));
            // todo: implement pop-up
            new Stage().show();
        });

        this.getStylesheets().add("styles.css");
        this.getStyleClass().add("daypane");
    }

    public String get() {
        return MESSAGE;
    }

    public void addEvent(Event event) {
        events.add(event);
        eventsBox.getChildren().add(event);
    }

    public boolean removeEvent(Event event) {
        boolean removed = events.remove(event);
        eventsBox.getChildren().remove(event);

        return removed;
    }

    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }
}
