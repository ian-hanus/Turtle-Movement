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
import java.util.Map;

public class ParameterWindow extends Stage {
    private String[] myParameterNames;
    private Double[] myParameterValues;
    private ArrayList<TextField> myFields;

    public ParameterWindow(String programName, String[] parameterNames){
        myParameterNames = parameterNames;
        myParameterValues = new Double[parameterNames.length];
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp);
        bp.setBottom(drawButtons());
        bp.setCenter(drawFieldGrid());
        this.sizeToScene();
        this.setTitle(programName);
        this.setScene(scene);
        this.setResizable(false);
        this.showAndWait();
    }

    public Node drawButtons() {
        HBox buttonBox = new HBox();
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> this.close());
        Button applyButton = new Button("Apply");
        applyButton.setOnAction(e -> setNewParams());
        buttonBox.getChildren().addAll(applyButton, cancelButton);
        return buttonBox;
    }

    private Node drawFieldGrid() {
        GridPane paramGrid = new GridPane();
        paramGrid.getStyleClass().add("legend-item");
        paramGrid.setHgap(10);
        int row = 0;
        for (String label : myParameterNames) {
            drawParamField(paramGrid, label, 0.0, row);
            row++;
        }
        return paramGrid;
    }

    private void drawParamField(GridPane paramGrid, String label, Double startVal, int row) {
        Text paramLabel = new Text(label);
        TextField paramField = new TextField();
        paramField.setText(Double.toString(startVal));
        myFields.add(paramField);
        paramGrid.add(paramLabel, 0, row);
        paramGrid.add(paramField, 1, row);
    }

    private void setNewParams(){
        for(int k = 0; k < myParameterValues.length; k++){
            myParameterValues[k] = Double.parseDouble(myFields.get(k).getText());
        }
    }

    public Double[] getNewParams(){
        return myParameterValues;
    }
}
