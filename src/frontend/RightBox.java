package frontend;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RightBox {
    private VBox myBox;

    public RightBox(String title){
        myBox = new VBox();
        myBox.getStyleClass().add("box-right");
        Label label = new Label(title);
        myBox.getChildren().add(label);
    }

    public VBox getBox(){
        return myBox;
    }
}
