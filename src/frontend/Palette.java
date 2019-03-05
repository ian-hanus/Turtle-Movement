package frontend;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.HashMap;
import java.util.Map;

public class Palette {
    private Map<Integer, Color> myColorMap;
    private Map<Integer, Shape> myShapeMap;

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
}
