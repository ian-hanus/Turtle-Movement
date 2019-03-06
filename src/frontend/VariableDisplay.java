package frontend;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariableDisplay {
    private Map<String, String> myVariableHistory;
    private List<String> myKeyOrder;
    private VBox myVariableBox;

    public VariableDisplay(){
        myVariableHistory = new HashMap<>();
        myKeyOrder = new ArrayList<>();
    }

    public void addVariable(String name, String value){
        myVariableHistory.put(name, value);
        myKeyOrder.add(name);
    }

    public String getVariable(int index){
        return myVariableHistory.get(myKeyOrder.get(index));
    }

    public Map<String, String> getVariables(){
        return myVariableHistory;
    }

    public String getVariableString(int index){
        String key = myKeyOrder.get(index);
        return key + " = " + myVariableHistory.get(key);
    }

    public void drawVariables(GridPane gridPane){
        myVariableBox = new RightBox("Current Variables").getBox();
        myVariableBox.getStyleClass().add("borderless-right");
        ScrollPane sp = new ScrollPane(myVariableBox);
        sp.getStyleClass().add("scroll-panes");
        gridPane.add(sp, 0, 1);
    }

    public void updateVariableDisplay(GridPane gridPane){
        drawVariables(gridPane);
        for(String s:this.getVariables().keySet()){
            Button variableButton = new Button(s + " " + this.getVariables().get(s));
            myVariableBox.getChildren().add(variableButton);
            variableButton.setOnAction(e -> openVariable(this, this.getVariables().get(s)));
        }
    }

    private void openVariable(VariableDisplay variableDisplay, String variable){
        VariableWindow vw = new VariableWindow(variableDisplay, variable);
    }
}
