import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javafx.geometry.Pos.CENTER;

public class DayPane extends BorderPane {

    private final String year;
    private final String month;
    private final String day;

    private VBox toDosBox; // todo: change to ScrollPane
    protected List<ToDo> toDos;

    public DayPane() {
        year = month = day = null;
    }

    public DayPane(int year, int month, int day) {
        this(year + "", month + "", day + "");
    }

    public DayPane(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;

        Text dayText = new Text(this.day);
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
            AddToDoPopUp popUp = new AddToDoPopUp(this);
            popUp.show();
        });
    }

    public int getYear() {
        return Integer.parseInt(year);
    }

    public int getMonth() {
        return Integer.parseInt(month);
    }

    public int getDay() {
        return Integer.parseInt(day);
    }

    public void addToDo(ToDo toDo) {
        toDos.add(toDo);
        toDosBox.getChildren().add(toDo);
    }

    public boolean removeToDo(ToDo toDo) {
        boolean removed = toDos.remove(toDo);
        toDosBox.getChildren().remove(toDo);

        return removed;
    }

    public List<ToDo> getToDos() {
        return Collections.unmodifiableList(toDos);
    }

    public void setColor(Color color) {
        setStyle("-fx-background-color: " + color.toString());
    }
}
