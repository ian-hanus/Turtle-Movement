package frontend;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TurtleStatusDisplay extends VBox {
    private Terminal myTerminal;
    private View myView;
    private List<TurtleSprite> turtleSpriteList;
    private TableView<TurtleSprite> turtleSpriteTableView;

    public TurtleStatusDisplay(Terminal terminal, View view, List<TurtleSprite> t){
        turtleSpriteTableView = getTable(t);
        this.getChildren().add(turtleSpriteTableView);
        myTerminal = terminal;
        myView = view;

    }

    public void refresh(List<TurtleSprite> turtleSprites){
        turtleSpriteTableView.getItems().clear();
        turtleSpriteTableView.getItems().addAll(turtleSprites);
    }



    private TableView getTable(List<TurtleSprite> turtles){
        TableView<TurtleSprite> tsTV = new TableView<>();
        tsTV.setEditable(true);
        TableColumn idCol = new TableColumn("Number");
        idCol.setCellValueFactory(
                new PropertyValueFactory<TurtleSprite, Integer>("ID")
        );
        TableColumn xCol = new TableColumn("X Position");
        xCol.setCellValueFactory(
                new PropertyValueFactory<TurtleState, Double>("X")
        );
        TableColumn yCol = new TableColumn("Y Position");
        yCol.setCellValueFactory(
                new PropertyValueFactory<TurtleState, Double>("Y")
        );
        TableColumn headingCol = new TableColumn("Direction");
        headingCol.setCellValueFactory(
                new PropertyValueFactory<TurtleState, Double>("Angle")
        );
        TableColumn penCol = new TableColumn("Pen Down?");
        penCol.setCellValueFactory(
                new PropertyValueFactory<TurtleState, Boolean>("PenDown")
        );
        TableColumn visibilityCol = new TableColumn("Visible?");
        visibilityCol.setCellValueFactory(
                new PropertyValueFactory<TurtleState, Boolean>("Visibility")
        );
        tsTV.getColumns().addAll(idCol, xCol, yCol, headingCol, penCol, visibilityCol);
        tsTV.getItems().addAll(turtles);
        return tsTV;
    };

}
