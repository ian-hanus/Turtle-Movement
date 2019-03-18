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

/**
 * Popup window used to set the value of variables
 */
public class VariableWindow extends Stage {
    private TextField myVariableField;

    /**
     * Constructor that creates and displays a popup allowing the user to change the value of a variable
     * @param variableDisplay is the VariableDisplay object with all of the possible variables set
     * @param variable is the String representing the name of the variable to be changed within the variableDisplay
     */
    public VariableWindow(VariableDisplay variableDisplay, String variable){
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp);
        bp.setBottom(drawButtons(variableDisplay, variable));
        bp.setCenter(drawFieldGrid(variable));
        this.sizeToScene();
        this.setTitle("Change variable value");
        this.setScene(scene);
        this.setResizable(false);
        this.show();
    }

    /**
     * Draws the buttons that allow the user to either set the value of the variable or cancel and escape the window
     * @param variableDisplay is the VariableDisplay object with all of the possible variables set
     * @param variable is the String representing the name of the variable to be changed within the variableDisplay
     * @return the Node containing the apply and cancel buttons available in the popup
     */
    public Node drawButtons(VariableDisplay variableDisplay, String variable) {
        HBox buttonBox = new HBox();
        Button applyButton = new Button("Apply");
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> this.close());
        applyButton.setOnAction(e -> variableDisplay.getVariables().put(variable, myVariableField.getText()));
        buttonBox.getChildren().addAll(applyButton, cancelButton);
        return buttonBox;
    }

    /**
     * Draws the textfield allowing a new value for a variable to be entered
     * @param variable
     * @return the box with the variable displayed next to a textfield for a value to be entered
     */
    public Node drawFieldGrid(String variable){
        GridPane variableGrid = new GridPane();
        variableGrid.getStyleClass().add("variable-field");
        variableGrid.setHgap(10);
        drawVariableGrid(variableGrid, variable);
        return variableGrid;
    }

    private void drawVariableGrid(GridPane variableGrid, String variable){
        Text variableLabel = new Text(variable);
        myVariableField = new TextField();
        myVariableField.setText("0");
        variableGrid.add(variableLabel, 0, 0);
        variableGrid.add(myVariableField, 1, 0);
    }
}
