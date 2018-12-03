import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddToDoPopUp extends PopUp<VBox> {

    private TextField tfToDoTitle;

//    private TextField tfStartHour, tfStartMinute;
//    private TextField tfEndHour, tfEndMinute;

    private Calendar today;

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

            dayPane.addToDo(toDo);
            this.close();
        });

        ComboBox<Color> cbColor = new ComboBox<>();
        cbColor.getItems().addAll(
                Color.LIGHTBLUE, Color.LIGHTPINK, Color.LIGHTGREEN,
                Color.LIGHTCYAN, Color.LIGHTYELLOW, Color.LIGHTGRAY
        );
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
//        cbColor.setButtonCell(new ListCell<>());
        cbColor.setOnAction(event -> toDo.setColor(cbColor.getValue()));

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(5);
        gridPane.addRow(0, new Label("Title: "), tfToDoTitle);
        gridPane.addRow(1, new Label("Color: "), cbColor);

        this.add(gridPane, btAdd);
        this.setTitle("Add Event");
    }

    public String getToDoTitle() {
        return tfToDoTitle.getText();
    }

    public Calendar getDate() {
        return today;
    }
}
