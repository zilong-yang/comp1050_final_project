import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PopUp<T extends Pane> extends Stage {

    private Scene scene;

    private T pane;

    public PopUp(T pane) {
        this.pane = pane;
        scene = new Scene(pane);
        this.setScene(scene);
    }

    public boolean add(Node... nodes) {
        return pane.getChildren().addAll(nodes);
    }

    public boolean remove(Node node) {
        return pane.getChildren().remove(node);
    }

    public boolean setAll(Node... nodes) {
        return pane.getChildren().setAll(nodes);
    }

    public T getPane() {
        return pane;
    }

    public void setPane(T newPane) {
        pane = newPane;
        scene.setRoot(newPane);
    }
}
