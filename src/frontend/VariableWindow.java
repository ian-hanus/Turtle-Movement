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

public class VariableWindow extends Stage {
    private TextField myVariableField;

    public VariableWindow(VariableDisplay variableDisplay, String variable){
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp);
        bp.setBottom(drawButtons(variableDisplay, variable));
        bp.setCenter(drawFieldGrid(variable));
        this.sizeToScene();
        this.setTitle("Edit Parameters");
        this.setScene(scene);
        this.setResizable(false);
        this.show();
    }

    public Node drawButtons(VariableDisplay variableDisplay, String variable) {
        HBox buttonBox = new HBox();
        Button applyButton = new Button("Apply");
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> this.close());
        applyButton.setOnAction(e -> variableDisplay.getVariables().put(variable, myVariableField.getText()));
        buttonBox.getChildren().addAll(applyButton, cancelButton);
        return buttonBox;
    }

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
