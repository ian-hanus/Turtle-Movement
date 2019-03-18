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

/**
 * Opens a box that allows the user to enter parameters for user defined commands to be rerun
 */
public class ParameterWindow extends Stage {
    private String[] myParameterNames;
    private Double[] myParameterValues;
    private ArrayList<TextField> myFields;

    /**
     * Constructor that opens the window used to enter the parameters to rerun the command
     * @param programName is the name of the user defined command
     * @param parameterNames is the list of parameters that will be used in the program
     */
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

    /**
     * Creates a row of buttons, one that will apply the parameters and run the command and another that cancels the
     * process and closes the window
     * @return a row of buttons that cancel or run the user defined command with the entered parameters
     */
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

    /**
     * Returns the new parameters entered by the user
     * @return an array of doubles corresponding to the parameters entered by the user
     */
    public Double[] getNewParams(){
        return myParameterValues;
    }
}
