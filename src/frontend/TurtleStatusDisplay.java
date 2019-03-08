package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import frontend.TurtleState;

import java.util.List;

public class TurtleStatusDisplay extends VBox {
    private Terminal myTerminal;
    private View myView;
    private List<TurtleSprite> turtleSpriteList;
    private TableView<TurtleState> turtleSpriteTableView;

    public TurtleStatusDisplay(Terminal terminal, View view, List<TurtleSprite> t){
        turtleSpriteTableView = getTable(t);
        this.getChildren().add(turtleSpriteTableView);
        myTerminal = terminal;
        myView = view;

    }

    public void refresh(List<TurtleSprite> turtleSprites){
        turtleSpriteTableView.getItems().clear();
        ObservableList<TurtleState> data = FXCollections.observableArrayList();
        for (TurtleSprite t : turtleSprites){
            data.add(new TurtleState(t.currState, true));
        }
        turtleSpriteTableView.setItems(data);
    }



    private TableView getTable(List<TurtleSprite> turtles){
        ObservableList<TurtleState> data = FXCollections.observableArrayList();
        for (TurtleSprite t : turtles){
            data.add(new TurtleState(t.currState, true));
        }

        TableView<TurtleState> tsTV = new TableView<>();
        tsTV.setEditable(true);
        TableColumn<TurtleState, Double> idCol = new TableColumn("ID");
        idCol.setMinWidth(50);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn xCol = new TableColumn("X Position");
        xCol.setCellValueFactory(new PropertyValueFactory<>("x"));
        TableColumn yCol = new TableColumn("Y Position");
        yCol.setCellValueFactory(new PropertyValueFactory<>("y"));
        TableColumn headingCol = new TableColumn("Direction");
        headingCol.setCellValueFactory(new PropertyValueFactory("angle"));
        TableColumn penCol = new TableColumn("Pen Down?");
        penCol.setCellValueFactory(new PropertyValueFactory<>("down"));
        TableColumn visibleCol = new TableColumn("Visible?");
        visibleCol.setCellValueFactory(new PropertyValueFactory<>("visible"));
        tsTV.getColumns().addAll(idCol, xCol, yCol, headingCol, penCol, visibleCol);
        tsTV.setItems(data);
        return tsTV;
    }


}
