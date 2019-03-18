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
import java.util.*;

import static javafx.scene.paint.Color.BLACK;

/**
 * Palette containing the information about available default and user defined shapes and colors that can be controlled
 * through commands on the backend
 */
public class Palette {
    private Map<Integer, Color> myColorMap;
    private Map<Integer, Shape> myShapeMap;
    private VBox myPaletteBox;

    private final static int DEFAULT_RECTANGLE_WIDTH = 15;
    private final static int DEFAULT_RECTANGLE_HEIGHT = 15;
    private final static int DEFAULT_RADIUS = 8;
    private final static int DEFAULT_ELLIPSE_RADIUS = 6;

    /**
     * Constructor that creates the list of available shapes and colors and displays the default values available to the
     * user
     */
    public Palette(){
        myColorMap = new HashMap<>();
        myShapeMap = new HashMap<>();
        establishDefaults();
    }

    private void establishDefaults(){
        int index = 1;
        Color[] colorDefaults = new Color[]{Color.RED, Color.BLUE, Color.GREEN};
        Shape[] shapeDefaults = new Shape[]{new Rectangle(DEFAULT_RECTANGLE_WIDTH, DEFAULT_RECTANGLE_HEIGHT),
                new Circle(DEFAULT_RADIUS), new Ellipse(DEFAULT_ELLIPSE_RADIUS, DEFAULT_RADIUS)};
        for(Color c:colorDefaults){
            this.addIndex(index, c);
            index++;
        }
        for(Shape s:shapeDefaults){
            this.addIndex(index, s);
            index++;
        }
    }

    /**
     * Method to add a new color object to the palette. Overwrites any other color with the index
     * @param index is the index that corresponds to the color
     * @param color is the desired color that will be able to be called by the index
     */
    public void addIndex(int index, Color color){
        myColorMap.put(index, color);
    }

    /**
     * Method to add a new shape object to the palette. Overwrites any other shape with the same index
     * @param index is the index corresponding to the desired shape
     * @param shape is the shape that the user can call by using the index
     */
    public void addIndex(int index, Shape shape){
        myShapeMap.put(index, shape);
    }

    private Map<Integer, Color> getColorMap(){
        return myColorMap;
    }

    private Map<Integer, Shape> getShapeMap(){
        return myShapeMap;
    }

    /**
     * Draws the palette with all established shapes and colors displayed for the user to choose from
     * @param pane is the pane in which the palette will be displayed
     */
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
