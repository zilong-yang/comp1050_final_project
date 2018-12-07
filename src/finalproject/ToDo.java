package finalproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class represent a to-do that can be put onto a day pane.
 *
 * @see DayPane
 */
public class ToDo extends StackPane {

    /**
     * The title of this to-do. Uses StringProperty to be compatible
     * with JavaFX features.
     */
    private StringProperty title;

    /**
     * The start and end date of this to-do.
     */
    private Calendar start, end;

    /**
     * The background of this pane.
     */
    private Rectangle background;

    /**
     * The color of this to-do, usually associated with its category.
     */
    private Color color;

    /**
     * Available colors for to-do's. Colors signify different categories.
     */
    public static Color[] AVAILABLE_COLORS = {
            Color.WHITE, Color.LIGHTPINK, Color.LIGHTGREEN,
            Color.LIGHTBLUE, Color.LIGHTGOLDENRODYELLOW, Color.LIGHTGRAY
    };

    /**
     * Creates a to-do with the given title. Initializes the graphical
     * components of this object.
     *
     * @param title the to-do title
     */
    public ToDo(String title) {
        this.title = new SimpleStringProperty(title);
        start = new GregorianCalendar();
        end = new GregorianCalendar();

        background = new Rectangle();
        background.setWidth(MonthPane.CELL_WIDTH - 10);
        background.setHeight(MonthPane.CELL_HEIGHT / 5);
        background.setFill(Color.WHITE);
        background.setStroke(((Color) background.getFill()).darker());

        Text text = new Text(title);
        text.setTextAlignment(TextAlignment.LEFT);
        text.textProperty().bind(this.title);

        this.getChildren().addAll(background, text);
    }

    // getters and setters
    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }

    public void setStart(int year, int month, int day) { setStart(year, month, day, 0, 0); }
    public void setStart(int year, int month, int day, int hour, int minute) { start.set(year, month, day, hour, minute); }
    public void setStart(Calendar c) { start = c; }

    public void setEnd(int year, int month, int day) { setEnd(year, month, day, 0, 0); }
    public void setEnd(int year, int month, int day, int hour, int minute) { end.set(year, month, day, hour, minute); }
    public void setEnd(Calendar c) { end = c; }

    public Color getColor() { return color; }
    public void setColor(Color color) {
        this.color = color;
        background.setFill(color);
        background.setStroke(color.darker());
    }
}
