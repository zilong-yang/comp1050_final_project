import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

import static javafx.geometry.Pos.CENTER;

/**
 * MonthPane is a VBox that shows the actual month layout with the
 * month name, the names of the days in a week (Sunday, Monday, ...),
 * and a GridPane that shows the days.
 */
public class MonthPane extends VBox {

    private final static String[] MONTHS = { "",
            "January", "February", "March", "April",
            "May", "June", "July", "August", "September",
            "October", "November", "December"
    };

    public static final double CELL_WIDTH = 150;
    public static final double CELL_HEIGHT  = 100;

    private final int month;

    protected GridPane days;

    public MonthPane(int month) {
        this.month = month;
        String strMonth = MONTHS[month];

        Label lblMonth = new Label(strMonth);
        lblMonth.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 30));
        lblMonth.setTextAlignment(TextAlignment.CENTER);

        HBox week = new HBox();
        week.setAlignment(CENTER);
        Label[] weekLabels = {
                new Label("Sunday"),
                new Label("Monday"),
                new Label("Tuesday"),
                new Label("Wednesday"),
                new Label("Thursday"),
                new Label("Friday"),
                new Label("Saturday"),
        };
        for (Label label : weekLabels) {
            label.setTextAlignment(TextAlignment.CENTER);
            label.setPrefWidth(CELL_WIDTH);
            label.setAlignment(CENTER);
        }
        week.setAlignment(CENTER);
        week.getChildren().addAll(weekLabels);

        days = new GridPane();
        int count = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                days.add(new DayPane(String.valueOf(count++)), j, i);
            }
        }

        week.setPrefWidth(days.getWidth());
        this.setAlignment(CENTER);
        this.getChildren().addAll(lblMonth, week, days);
    }

    public int getMonth() {
        return month;
    }

}
