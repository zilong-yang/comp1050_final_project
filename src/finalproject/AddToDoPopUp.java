package finalproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This represents a popup window for the user to add a to-do to
 * a day pane.
 */
public class AddToDoPopUp extends PopUp<VBox> {

    /**
     * The text field in which the user types in the to-do title.
     */
    private TextField tfToDoTitle;

    /**
     * The date of the to-do.
     */
    private Calendar today;

    /**
     * Creates a popup window that is able to add a to-do object
     * to the given day pane.
     *
     * @param dayPane   the day pane in which a to-do will be added
     */
    public AddToDoPopUp(DayPane dayPane) {
        super(new VBox(5));

        VBox parent = this.getPane();
        parent.setAlignment(Pos.CENTER);
        parent.setPadding(new Insets(10));

        tfToDoTitle = new TextField();
        tfToDoTitle.setPromptText("Title");

        //noinspection MagicConstant
        today = new GregorianCalendar(
                dayPane.getYear(),
                dayPane.getMonth() - 1,
                dayPane.getDay()
        );

        Button btAdd = new Button("Add");
        final ToDo toDo = new ToDo("");
        btAdd.setOnAction(event -> {
            toDo.setTitle(getToDoTitle());

            Tooltip toDoInfo = new Tooltip(
                    String.format("%s, %d/%d/%d", getToDoTitle(), dayPane.getMonth(),
                            dayPane.getDay(), dayPane.getYear())
            );
            Tooltip.install(toDo, toDoInfo);

            toDo.setOnMouseClicked(mouse -> {
                if (mouse.getButton() == MouseButton.SECONDARY)
                    dayPane.removeToDo(toDo);
            });

            dayPane.addToDo(toDo);
            this.close();
        });

        ComboBox<Color> cbColor = new ComboBox<>();
        cbColor.getItems().addAll(ToDo.AVAILABLE_COLORS);
        cbColor.setCellFactory(callback -> new ListCell<>() {
            private final Rectangle r; {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                r = new Rectangle(50, 10);
            }

            @Override
            protected void updateItem(Color item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty)
                    setGraphic(null);
                else {
                    r.setFill(item);
                    setGraphic(r);
                }
            }
        });
//        cbColor.getSelectionModel().selectFirst();
        cbColor.setValue(cbColor.getItems().get(0));
        cbColor.setButtonCell(new ListCell<>() {
            private final Rectangle r; {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                r = new Rectangle(50, 10);
            }

            @Override
            protected void updateItem(Color item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty)
                    setGraphic(null);
                else {
                    r.setFill(item);
                    setGraphic(r);
                }
            }
        });
        cbColor.setOnAction(event -> toDo.setColor(cbColor.getValue()));

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(5);
        gridPane.addRow(0, new Label("Title: "), tfToDoTitle);
        gridPane.addRow(1, new Label("Color: "), cbColor);

        this.add(gridPane, btAdd);
        this.setTitle("Add Event");
    }

    /**
     * Returns the content in the text field for title.
     *
     * @return  the text in tfToDoTitle
     */
    public String getToDoTitle() {
        return tfToDoTitle.getText();
    }

    /**
     * Returns the associated date of the to-do.
     *
     * @return  the Calendar representing the date
     */
    public Calendar getDate() {
        return today;
    }
}
