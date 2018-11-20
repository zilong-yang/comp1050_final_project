import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import static javafx.geometry.Pos.CENTER;

public class MonthPane extends VBox {

    private final static String[] MONTHS = { "",
            "January", "February", "March", "April",
            "May", "June", "July", "August", "September",
            "October", "November", "December"
    };

    private final int month;
    private final String strMonth;
    private final Label lblMonth;

    private static HBox weekLabels;

    protected GridPane days;

    public MonthPane(int month) {
        this.month = month;
        strMonth = MONTHS[month];

        lblMonth = new Label(strMonth);
        lblMonth.setTextAlignment(TextAlignment.CENTER);

        weekLabels = new HBox();
        Label[] week = {
                new Label("Sunday"),
                new Label("Monday"),
                new Label("Tuesday"),
                new Label("Wednesday"),
                new Label("Thursday"),
                new Label("Friday"),
                new Label("Saturday"),
        };
        weekLabels.setAlignment(CENTER);
        weekLabels.getChildren().addAll(week);

        days = new GridPane();
        int count = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                days.add(new DayPane(count++), j, i);
            }
        }

        this.setAlignment(CENTER);
        this.getChildren().addAll(lblMonth, weekLabels, days);
    }

    public int getMonth() {
        return month;
    }

    private static class DayPane extends VBox {

        int day;
        String dayStr;

        DayPane(int day) {
            this.day = day;
            dayStr = String.valueOf(day);

            Text txtDay = new Text(dayStr);

            this.getChildren().add(txtDay);
            this.setAlignment(CENTER);
            this.setPrefWidth(120);
            this.setPrefHeight(100);
            this.getStylesheets().add("styles.css");
            this.getStyleClass().add("daypane");
        }

        public int getDay() {
            return day;
        }
    }
}
