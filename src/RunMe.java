import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunMe extends Application {

    @Override
    public void start(Stage primaryStage) {
        MonthPane month = new MonthPane(2018, 12);

        primaryStage.setScene(new Scene(month));
        primaryStage.setTitle("Planner");
        primaryStage.show();
    }
}
