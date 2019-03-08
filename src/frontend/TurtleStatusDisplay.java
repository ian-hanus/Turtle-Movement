package frontend;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
        turtleSpriteTableView = getTable(turtleSprites);
    }



    private TableView getTable(List<TurtleSprite> turtles){
        TableView<TurtleSpriteProps> tsTV = new TableView<>();
//        tsTV.setEditable(true);
//        TableColumn idCol = new TableColumn("Number");
//        idCol.setCellValueFactory(
//                new PropertyValueFactory<TurtleSpriteProps, Integer>("id")
//        );
//        TableColumn xCol = new TableColumn("X Position");
//        xCol.setCellValueFactory(
//                new PropertyValueFactory<TurtleSpriteProps, Double>("X")
//        );
//        TableColumn yCol = new TableColumn("Y Position");
//        yCol.setCellValueFactory(
//                new PropertyValueFactory<TurtleSpriteProps, Double>("Y")
//        );
//        TableColumn headingCol = new TableColumn("Direction");
//        headingCol.setCellValueFactory(
//                new PropertyValueFactory<TurtleSpriteProps, Double>("angle")
//        );
//        TableColumn penCol = new TableColumn("Pen Down?");
//        penCol.setCellValueFactory(
//                new PropertyValueFactory<TurtleSpriteProps, Boolean>("down")
//        );
//        TableColumn visibilityCol = new TableColumn("Visible?");
//        visibilityCol.setCellValueFactory(
//                new PropertyValueFactory<TurtleSpriteProps, Boolean>("visible")
//        );
//        tsTV.getColumns().addAll(idCol, xCol, yCol, headingCol, penCol, visibilityCol);
//        for(TurtleSprite t : turtles){
//            tsTV.getItems().add(new TurtleSpriteProps(t));
//        }
        return tsTV;
    }

    public static class TurtleSpriteProps {
        private final SimpleIntegerProperty id;
        private final SimpleDoubleProperty X;
        private final SimpleDoubleProperty Y;
        private final SimpleDoubleProperty angle;
        private final SimpleBooleanProperty down;
        private final SimpleBooleanProperty visible;

        public TurtleSpriteProps(TurtleSprite t){
            id = new SimpleIntegerProperty(t.getID());
            X = new SimpleDoubleProperty(t.getX());
            Y = new SimpleDoubleProperty(t.getY());
            angle = new SimpleDoubleProperty(t.getAngle());
            down = new SimpleBooleanProperty(t.isPenDown());
            visible = new SimpleBooleanProperty(t.isVisibility());

            System.out.println(id);
            System.out.println(X);
            System.out.println(Y);
            System.out.println(angle);
            System.out.println(down);
            System.out.println(visible);

        }

        public Integer getId() {
            return id.get();
        }
        public Double getX() {
            return X.get();
        }

        public Double getY() {
            return Y.get();
        }
        public Double getAngle() {
            return angle.get();
        }
        public Boolean getDown() {
            return down.get();
        }

        public Boolean getVisible() {
            return visible.get();
        }

//        public SimpleIntegerProperty idProperty() {
//            return id;
//        }
//
//
//        public SimpleDoubleProperty xProperty() {
//            return X;
//        }
//
//        public SimpleDoubleProperty yProperty() {
//            return Y;
//        }
//
//        public SimpleDoubleProperty angleProperty() {
//            return angle;
//        }
//        public SimpleBooleanProperty downProperty() {
//            return down;
//        }
//        public SimpleBooleanProperty visibleProperty() {
//            return visible;
//        }

    }


}
