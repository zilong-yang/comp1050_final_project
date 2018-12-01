import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddEventPopUp extends PopUp<VBox> {

    private VBox parent;

    private TextField tfToDoTitle;

//    private TextField tfStartHour, tfStartMinute;
//    private TextField tfEndHour, tfEndMinute;

    private Calendar today;

    public AddEventPopUp(DayPane dayPane) {
        super(new VBox(5));

        parent = this.getPane();
        parent.setAlignment(Pos.CENTER);
        parent.setPadding(new Insets(10));

        tfToDoTitle = new TextField();

/*
        tfStartHour = new TextField();
        tfStartHour.setPrefColumnCount(2);

        tfStartMinute = new TextField();
        tfStartMinute.setPrefColumnCount(2);

        tfEndHour = new TextField();
        tfEndHour.setPrefColumnCount(2);

        tfEndMinute = new TextField();
        tfEndMinute.setPrefColumnCount(2);
*/

        today = new GregorianCalendar(dayPane.getYear(), dayPane.getMonth(), dayPane.getDay());

        Button btAdd = new Button("Add");
        btAdd.setOnAction(event -> {
            ToDo toDo = new ToDo(getToDoTitle());
            dayPane.addToDo(toDo);
            this.close();
        });

        GridPane gridPane = new GridPane();
        gridPane.addRow(0, new Label("Title: "), tfToDoTitle);

        this.add(gridPane, btAdd);

        this.setTitle("Add Event");
    }

    public String getToDoTitle() {
        return tfToDoTitle.getText();
    }

    public Calendar getDate() {
        return today;
    }

/*
    public int getStartHour() {
        return Integer.parseInt(tfStartHour.getText());
    }

    public int getStartMinute() {
        return Integer.parseInt(tfStartMinute.getText());
    }

    public int getEndHour() {
        return Integer.parseInt(tfEndHour.getText());
    }

    public int getEndMinute() {
        return Integer.parseInt(tfEndMinute.getText());
    }
*/
}
