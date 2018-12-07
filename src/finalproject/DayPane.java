package finalproject;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javafx.geometry.Pos.CENTER;

/**
 * This class represents a day in a calendar/planner. The user
 * can interact with the pane to add or remove to-do's.
 */
public final class DayPane extends BorderPane {

    /**
     * The specific date of this day pane.
     */
    private final String year;
    private final String month;
    private final String day;

    /**
     * The list of to-do's of this day and its graphical representation.
     */
    private final VBox toDosBox;
    protected final List<ToDo> toDos;

    /**
     * Creates an empty DayPane that has no event and initializes
     * all fields to null. Note that there is no information stored
     * in an instance created by this constructor.
     */
    public DayPane() {
        year = month = day = null;
        toDosBox = null;
        toDos = null;
    }

    /**
     * Creates a DayPane with the given year, month, and day as
     * three integers.
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     */
    public DayPane(int year, int month, int day) {
        this(year + "", month + "", day + "");
    }

    /**
     * Creates and initializes a DayPane with the given year, month,
     * and day as three Strings. This constructor is intended for
     * internal use.
     *
     * @see #DayPane(int, int, int)
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     */
    protected DayPane(String year, String month, String day) {
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

        final Insets DEFAULT_PADDING = new Insets(5);
        this.setPadding(DEFAULT_PADDING);

        this.setOnMouseClicked(event -> {
            AddToDoPopUp popUp = new AddToDoPopUp(this);
            popUp.show();
        });

        final double BORDER_WIDTH = 2.5;
        this.setOnMouseEntered(event -> {
                this.setBorder(new Border(new BorderStroke(
                        Color.GRAY,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(BORDER_WIDTH)
                )));

                this.setPadding(new Insets(BORDER_WIDTH));
        });

        this.setOnMouseExited(event -> {
            this.setPadding(DEFAULT_PADDING);
            this.setBorder(Border.EMPTY);
        });
    }

    // getters
    public int getYear()    { return Integer.parseInt(year); }
    public int getMonth()   { return Integer.parseInt(month); }
    public int getDay()     { return Integer.parseInt(day); }

    /**
     * Adds a to-do to this day.
     *
     * @param toDo  the to-do being added
     */
    public void addToDo(ToDo toDo) {
        toDos.add(toDo);
        toDosBox.getChildren().add(toDo);
    }

    /**
     * Deletes a to-do from this day.
     *
     * @param toDo  the to-do being deleted
     * @return  true if deleted successfully
     */
    public boolean removeToDo(ToDo toDo) {
        boolean removed = toDos.remove(toDo);
        toDosBox.getChildren().remove(toDo);

        return removed;
    }

    /**
     * Returns the list of to-do's in this day pane.
     *
     * @return  a list of to-do's.
     */
    public List<ToDo> getToDos() {
        return Collections.unmodifiableList(toDos);
    }

    /**
     * Changes the background color of this pane.
     *
     * @param color the new color
     */
    public void setColor(Color color) {
        setBackground(new Background(
                new BackgroundFill(color, null, null)));
    }
}
