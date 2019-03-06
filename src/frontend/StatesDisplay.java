package frontend;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StatesDisplay {



    public TableView getTable(){
        TableView<TurtleSprite> turtleSpriteTableView = new TableView<>();
        turtleSpriteTableView.setEditable(true);
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
                new PropertyValueFactory<TurtleState, Boolean>("Visible")
        );
        turtleSpriteTableView.getColumns().addAll(idCol, xCol, yCol, headingCol, penCol, visibilityCol);
       // turtleSpriteTableView.getItems().addAll(TURTLESPRITES)
        return null;
    };

}
