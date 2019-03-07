package frontend;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ReturnWindow extends Stage {
    private final int RETURN_WIDTH = 200;
    private final int RETURN_HEIGHT = 200;
    private final String STYLE_SHEET = "/GUIResources/ViewFormat.css";

    public ReturnWindow(double returnValue){
        Pane p = new Pane();
        Scene scene = new Scene(p);
        scene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
        p.getStyleClass().add("return-pane");
        p.getChildren().add(new Label("Return Value: "+ returnValue));
        this.sizeToScene();
        this.setTitle("Edit Parameters");
        this.setScene(scene);
        this.setResizable(false);
        this.show();
    }
}
