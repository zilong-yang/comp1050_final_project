package finalproject;

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
public final class MonthPane extends VBox {

    /**
     * The month names in order, from January to December.
     * MONTHS[0] = January, MONTHS[1] = February, ..., MONTHS[11] = December
     */
    public final static String[] MONTHS = {
            "January", "February", "March", "April",
            "May", "June", "July", "August", "September",
            "October", "November", "December"
    };

    /**
     * Width and height of the children in days. Used to initialize
     * DayPanes as well as their components.
     *
     * @see ToDo#ToDo(String)
     */
    protected static final double CELL_WIDTH = 150;
    protected static final double CELL_HEIGHT  = 100;

    /**
     * The year of this month.
     */
    private final int year;

    /**
     * The int representation of this month.
     * 0 - January to 11 - December.
     */
    private final int month;

    /**
     * The GridPane that holds the graphical days in this month.
     */
    private final GridPane days;

    /**
     * Creates a MonthPane of the given year and month. Initializes
     * all components in this pane.
     *
     * @param year  the year
     * @param month the month (1 being January and 12 December in this case)
     */
    public MonthPane(int year, int month) {
        this.year = year;
        this.month = month;

        Label lblMonth = new Label(MONTHS[month - 1]);
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

        Calendar c = new GregorianCalendar(year, month - 1, 1);

        // Initializes days with a total of 42 DayPanes. Depending on
        // the availability, they will be colored differently. Note that
        // this creates DayPanes of real dates (e.g. December of 2018
        // started on a Saturday. Therefore, the creation of a MonthPane
        // with parameters 2018 and 12 would start on a Saturday.
        days = new GridPane();

        int count = -c.get(Calendar.DAY_OF_WEEK) + 1;
        int total = daysInMonth(year, month);
        int totalPrevMonth = daysInMonth(year, month - 1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (count >= 0 && count < total) {
                    DayPane dayPane = new DayPane(year, month, count + 1);
                    days.add(dayPane, j, i);

                    if (j == 0 || j == 6)
                        dayPane.setColor((Color.LIGHTYELLOW));
                } else {
                    DayPane dayPane = new DayPane();
                    days.add(dayPane, j, i);
                    dayPane.setColor(Color.LIGHTGREY);
                    dayPane.setDisable(true);
                }

                count++;
            }
        }
        days.setGridLinesVisible(true);

        week.setPrefWidth(days.getWidth());
        this.setAlignment(CENTER);
        this.getChildren().addAll(lblMonth, week, days);
    }

    /**
     * Returns the month of this month pane.
     *
     * @return  the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the year of this month pane.
     *
     * @return  the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the total number of days in a specific year and month.
     * This method takes into account leap years.
     *
     * @param year  the year
     * @param month the month
     * @return  the number of days
     */
    public static int daysInMonth(int year, int month) {
        switch (month) {
            case 1: case 3: case 5: case 7:
            case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            default: return isLeapYear(year) ? 29 : 28;
        }
    }

    /**
     * Returns the name of a month.
     *
     * @param month the month number (1-12)
     * @return  the name of month
     */
    public static String getMonthName(int month) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Invalid month: " + month);

        return MONTHS[month - 1];
    }

    /**
     * Returns whether or not a year is leap year.
     *
     * @param year  the year
     * @return  true if year is leap year; false otherwise
     */
    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0) || year % 400 == 0);
    }
}
