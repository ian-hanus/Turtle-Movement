package frontend;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

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
        turtleSpriteTableView = getTable(turtleSprites);
    }



    private TableView getTable(List<TurtleSprite> turtles){
        //ObservableList<TurtleSpriteProps> data = new FX.bservableArrayList<>();

        TableView<TurtleSpriteProps> tsTV = new TableView<>();
        tsTV.setEditable(true);
        TableColumn idCol = new TableColumn("Number");
        idCol.setCellValueFactory(
                new PropertyValueFactory<TurtleSpriteProps, Integer>("id")
        );
        TableColumn xCol = new TableColumn("X Position");
        xCol.setCellValueFactory(
                new PropertyValueFactory<TurtleSpriteProps, Double>("X")
        );
        TableColumn yCol = new TableColumn("Y Position");
        yCol.setCellValueFactory(
                new PropertyValueFactory<TurtleSpriteProps, Double>("Y")
        );
        TableColumn headingCol = new TableColumn("Direction");
        headingCol.setCellValueFactory(
                new PropertyValueFactory<TurtleSpriteProps, Double>("angleProp")
        );
        TableColumn penCol = new TableColumn("Pen Down?");
        penCol.setCellValueFactory(
                new PropertyValueFactory<TurtleSpriteProps, Boolean>("downProp")
        );
        TableColumn visibilityCol = new TableColumn("Visible?");
        visibilityCol.setCellValueFactory(
                new PropertyValueFactory<TurtleSpriteProps, Boolean>("visibleProp")
        );
        tsTV.getColumns().addAll(idCol, xCol, yCol, headingCol, penCol, visibilityCol);
        for(TurtleSprite t : turtles){
            tsTV.getItems().add(new TurtleSpriteProps(t));
        }
        return tsTV;
    }


}
