import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javafx.geometry.Pos.CENTER;

public class DayPane extends BorderPane {

    private final String MESSAGE;

    private VBox toDosBox; // todo: change to ScrollPane
    protected List<ToDo> toDos;

    DayPane(String day) {
        this.MESSAGE = day;

        Text dayText = new Text(MESSAGE);
        this.setTop(dayText);
        setAlignment(dayText, CENTER);

        toDosBox = new VBox();
        toDosBox.setAlignment(CENTER);
        this.setBottom(toDosBox);
        setAlignment(toDosBox, CENTER);

        toDos = new ArrayList<>();

        this.setPrefWidth(MonthPane.CELL_WIDTH);
        this.setPrefHeight(MonthPane.CELL_HEIGHT);
        this.setOnMouseClicked(event -> {
                addEvent(new ToDo("hello"));
            // todo: implement pop-up
//            new Stage().show();
        });

        this.getStylesheets().add("styles.css");
        this.getStyleClass().add("daypane");
    }

    public String get() {
        return MESSAGE;
    }

    public void addEvent(ToDo toDo) {
        toDos.add(toDo);
        toDosBox.getChildren().add(toDo);
    }

    public boolean removeEvent(ToDo toDo) {
        boolean removed = toDos.remove(toDo);
        toDosBox.getChildren().remove(toDo);

        return removed;
    }

    public List<ToDo> getToDos() {
        return Collections.unmodifiableList(toDos);
    }
}
