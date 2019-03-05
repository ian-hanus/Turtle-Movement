package frontend;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class ParameterWindow extends Stage {
    public ParameterWindow(){
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp);
        bp.setBottom(drawButtons());
        bp.setCenter(drawFieldGrid());
        this.sizeToScene();
        this.setTitle("Edit Parameters");
        this.setScene(scene);
        this.setResizable(false);
        this.show();
    }

    public Node drawButtons() {
        HBox controlBox = new HBox();
        controlBox.getStyleClass().add("controls");
        Button applyButton = new Button("Apply");
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> this.close());
        controlBox.getChildren().add(applyButton);
        controlBox.getChildren().add(cancelButton);
        return controlBox;
    }

    private Node drawFieldGrid() {
        List<String> paramList = new ArrayList<>();
        paramList.addAll(Arrays.asList("Test1", "Test2"));
        GridPane paramGrid = new GridPane();
        paramGrid.getStyleClass().add("param");
        paramGrid.setHgap(10);
        int row = 0;
        for (String label : paramList) {
            drawParamField(paramGrid, label, 1, row);
            row++;
        }
        return paramGrid;
    }

    private void drawParamField(GridPane paramGrid, String label, double startVal, int row) {
        Text paramLabel = new Text(label);
        TextField paramField = new TextField();
        paramField.setText(Double.toString(startVal));
        paramGrid.add(paramLabel, 0, row);
        paramGrid.add(paramField, 1, row);
    }
}
