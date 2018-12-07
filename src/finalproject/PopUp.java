package finalproject;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * A general class for popup window.
 *
 * @param <T>   the Pane to display in this stage
 */
public class PopUp<T extends Pane> extends Stage {

    /**
     * The scene for this stage.
     */
    private Scene scene;

    /**
     * The pane in the scene.
     */
    private T pane;

    /**
     * Creates a stage with a scene of the given pane.
     *
     * @param pane  the pane
     */
    public PopUp(T pane) {
        this.pane = pane;
        scene = new Scene(pane);
        this.setScene(scene);
    }

    /**
     * Directly adds nodes to the provided pane in this stage.
     *
     * @param nodes the nodes to be added
     * @return  true if the nodes are added successfully
     */
    public boolean add(Node... nodes) {
        return pane.getChildren().addAll(nodes);
    }

    /**
     * Directly deletes a node from the provided pane.
     *
     * @param node  the node to be removed
     * @return  true if the node has been successfully removed
     */
    public boolean remove(Node node) {
        return pane.getChildren().remove(node);
    }

    /**
     * Replaces all nodes with the given array of nodes in the pane.
     *
     * @param nodes the nodes to replace with
     * @return  true if the nodes are successfully replaced
     */
    public boolean setAll(Node... nodes) {
        return pane.getChildren().setAll(nodes);
    }

    /**
     * Returns the provided pane.
     *
     * @return  the pane
     */
    public T getPane() {
        return pane;
    }

    /**
     * Changes the pane to the given new pane.
     *
     * @param newPane   the new pane
     */
    public void setPane(T newPane) {
        pane = newPane;
        scene.setRoot(newPane);
    }
}
