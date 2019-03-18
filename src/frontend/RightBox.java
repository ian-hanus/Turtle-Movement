package frontend;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Creates a new displayable box with format common to other display boxes
 */
public class RightBox {
    private VBox myBox;

    /**
     * Creates a new display box to the right with the entered title
     * @param title is the desired title of the box
     */
    public RightBox(String title){
        myBox = new VBox();
        myBox.getStyleClass().add("box-right");
        Label label = new Label(title);
        myBox.getChildren().add(label);
    }

    /**
     * Returns the created box
     * @return the VBox created
     */
    public VBox getBox(){
        return myBox;
    }
}
