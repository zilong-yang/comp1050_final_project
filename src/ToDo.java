import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ToDo extends StackPane {

    private StringProperty title;
    private Calendar start, end;

    private Rectangle background;
    private Color color;

    public static Color[] AVAILABLE_COLORS = {
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
            Color.ORANGE, Color.PURPLE, Color.CYAN, Color.GREY
    };

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

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setStart(int year, int month, int day) {
        setStart(year, month, day, 0, 0);
    }

    public void setStart(int year, int month, int day, int hour, int minute) {
        start.set(year, month, day, hour, minute);
    }

    public void setStart(Calendar c) {
        start = c;
    }

    public void setEnd(int year, int month, int day) {
        setEnd(year, month, day, 0, 0);
    }

    public void setEnd(int year, int month, int day, int hour, int minute) {
        end.set(year, month, day, hour, minute);
    }

    public void setEnd(Calendar c) {
        end = c;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        background.setFill(color);
        background.setStroke(color.darker());
    }
}
