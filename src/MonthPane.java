import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

    private final int year;
    private final int month;

    protected GridPane days;

    public MonthPane(int year, int month) {
        this.year = year;

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

        @SuppressWarnings("MagicConstant")
        Calendar c = new GregorianCalendar(year, month - 1, 1);

        days = new GridPane();

        int count = -c.get(Calendar.DAY_OF_WEEK) + 1;
        int total = daysInMonth(year, month);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (count >= 0 && count < total) {
                    DayPane dayPane = new DayPane(year, month, count);
                    days.add(dayPane, j, i);
                } else {
                    DayPane dayPane = new DayPane();
                    days.add(dayPane, j, i);
                    dayPane.setColor(Color.LIGHTGREY);
                }

                count++;
            }
        }
        days.setGridLinesVisible(true);

        week.setPrefWidth(days.getWidth());
        this.setAlignment(CENTER);
        this.getChildren().addAll(lblMonth, week, days);
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public static int daysInMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default: {
                if (new GregorianCalendar().isLeapYear(year))
                    return 29;
                return 28;
            }
        }
    }

    public static String getMonthName(int month) {
        return MONTHS[month];
    }
}
