package frontend;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

import static javafx.scene.paint.Color.BLACK;

public class Palette {
    private Map<Integer, Color> myColorMap;
    private Map<Integer, Shape> myShapeMap;
    private VBox myPaletteBox;

    public Palette(){
        myColorMap = new HashMap<>();
        myShapeMap = new HashMap<>();
        establishDefaults();
    }

    private void establishDefaults(){
        this.addColorIndex(1, Color.RED);
        this.addColorIndex(2, Color.BLUE);
        this.addColorIndex(3, Color.GREEN);
        this.addShapeIndex(4, new Rectangle(15, 15));
        this.addShapeIndex(5, new Circle(8));
        this.addShapeIndex(6, new Ellipse(6, 8));
    }

    public void addColorIndex(int index, Color color){
        myColorMap.put(index, color);
    }

    public void addShapeIndex(int index, Shape shape){
        myShapeMap.put(index, shape);
    }

    public Map<Integer, Color> getColorMap(){
        return myColorMap;
    }

    public Map<Integer, Shape> getShapeMap(){
        return myShapeMap;
    }

    public void drawPalette(GridPane pane){
        myPaletteBox = new RightBox("Palette").getBox();
        myPaletteBox.getStyleClass().add("borderless-right");
        ScrollPane sp = new ScrollPane(myPaletteBox);
        sp.getStyleClass().add("scroll-panes");
        pane.add(sp, 1, 1);
        for(Integer i:this.getColorMap().keySet()){
            paletteRow(i, this.getColorMap().get(i));
        }
        for(Integer i:this.getShapeMap().keySet()){
            paletteRow(i, this.getShapeMap().get(i));
        }
    }

    private void paletteRow(int index, Color color){
        HBox colorRow = new HBox();
        colorRow.getStyleClass().add("color-picker-row");
        Text indexTest = new Text(index + " - Color: ");
        Rectangle colorSample = new Rectangle(15, 15);
        colorSample.setFill(color);
        colorRow.getChildren().addAll(indexTest, colorSample);
        myPaletteBox.getChildren().add(colorRow);
    }

    private void paletteRow(int index, Shape shape){
        HBox shapeRow = new HBox();
        shapeRow.getStyleClass().add("color-picker-row");
        Text indexTest = new Text(index + " - Shape: ");
        shape.setFill(BLACK);
        shapeRow.getChildren().addAll(indexTest, shape);
        myPaletteBox.getChildren().add(shapeRow);
    }
}
