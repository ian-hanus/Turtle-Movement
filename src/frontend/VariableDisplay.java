package frontend;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains the available variables, displaying them for the user so they can be used or changed by clicking on the
 * button corresponding to the variable value
 */
public class VariableDisplay {
    private Map<String, String> myVariableHistory;
    private List<String> myKeyOrder;
    private VBox myVariableBox;
    private GridPane myPane;

    /**
     * Creates the instance variables necessary for keeping track of and displaying the variables
     * @param gridPane
     */
    public VariableDisplay(GridPane gridPane){
        myVariableHistory = new HashMap<>();
        myKeyOrder = new ArrayList<>();
        myPane = gridPane;
    }

    /**
     * Adds a single variable to the list of available variables
     * @param name is the variable name
     * @param value is the value of the variable
     */
    public void addVariable(String name, String value){
        myVariableHistory.put(name, value);
        myKeyOrder.add(name);
    }

    /**
     * Sets the variables equal to a map of the names and their values, used to load in old variables from the library
     * @param variableHistory is the map corresponding to the new desired variables
     */
    public void setVariables(Map<String, String> variableHistory){
        myVariableHistory = variableHistory;
        updateVariableDisplay();
    }

    /**
     * Returns the map of variables to be saved in a library
     * @return the map of names and value corresponding to the variables
     */
    public Map<String, String> getVariables(){
        return myVariableHistory;
    }

    /**
     * Draws and formats the display box used to show variables
     */
    public void drawVariables(){
        myVariableBox = new RightBox("Current Variables").getBox();
        myVariableBox.getStyleClass().add("borderless-right");
        ScrollPane sp = new ScrollPane(myVariableBox);
        sp.getStyleClass().add("scroll-panes");
        myPane.add(sp, 0, 1);
    }

    /**
     * Updates the variable display to reflect any changes to it after a command run
     */
    public void updateVariableDisplay(){
        drawVariables();
        for(String s:this.getVariables().keySet()){
            Button variableButton = new Button(s + " " + this.getVariables().get(s));
            myVariableBox.getChildren().add(variableButton);
            variableButton.setOnAction(e -> openVariable(this, this.getVariables().get(s)));
        }
    }

    /**
     * Opens popup to change the value of the variable
     * @param variableDisplay
     * @param variable
     */
    private void openVariable(VariableDisplay variableDisplay, String variable){
        VariableWindow vw = new VariableWindow(variableDisplay, variable);
    }
}
