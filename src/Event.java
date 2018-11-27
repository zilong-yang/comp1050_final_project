import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event extends StackPane {

    private String title;

    private Calendar start, end;

    public Event(String title) {
        this.title = title;
        start = new GregorianCalendar();
        end = new GregorianCalendar();

        Rectangle rect = new Rectangle();
        rect.setWidth(MonthPane.CELL_WIDTH - 5);
        rect.setHeight(MonthPane.CELL_HEIGHT / 5);
        rect.setFill(Color.WHITE);
        rect.setStroke(((Color) rect.getFill()).darker());

        Text text = new Text(title);
        text.setTextAlignment(TextAlignment.LEFT);

        this.getChildren().addAll(rect, text);
        this.setOnMouseClicked(event -> {
            // todo: implement pop-up
        });
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStart(int year, int month, int day) {
        setStart(year, month, day, 0, 0);
    }

    public void setStart(int year, int month, int day, int hour, int minute) {
        start.set(year, month, day, hour, minute);
    }

    public void setEnd(int year, int month, int day) {
        setEnd(year, month, day, 0, 0);
    }

    public void setEnd(int year, int month, int day, int hour, int minute) {
        end.set(year, month, day, hour, minute);
    }
}
