package frontend;


import javafx.geometry.Bounds;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.input.MouseEvent;

import java.util.List;


public class TurtleSprite extends ImageView {
    public int getNum() {
        return num;
    }

    private int num;
    private List<Line> myLines;
    private final double TURTLE_SIZE = 30;
    TurtleState currState;
    Image activeImage;
    private boolean isActive;

    public TurtleSprite(){
        super();
        this.setFitHeight(TURTLE_SIZE);
        this.setFitWidth(TURTLE_SIZE);
        this.setPreserveRatio(true);
        this.setOnMouseClicked((MouseEvent e) -> this.toggleActive());
    }
    public TurtleSprite(TurtleState ts, double h, double w){
        this.setX((ts.getX() + w/2) % w);
        this.setY((-ts.getY() + h/2 ) % h);
        this.setRotate(ts.getAngle());

        currState = ts;
        if (currState.isVisible()){
            setTurtleImage(activeImage);
        } else {
            this.setImage(null);
        }
    }

    public void clearLines(){
        myLines.clear();
    }

    public void setTurtle(TurtleState ts, double h, double w){

        this.setX((ts.getX() + w/2) % w);
        this.setY((-ts.getY() + h/2 ) % h);
        this.setRotate(ts.getAngle());

        currState = ts;
        if (currState.isVisible()){
            setTurtleImage(activeImage);
        } else {
            this.setImage(null);
        }
    }

    public void setTurtleImage(Image i) {
        this.setImage(i);
        activeImage = i;

    }

    public int getID(){
        return currState.getID();
    }
    public boolean getPenDown(){
        return currState.isDown();
    }
    public boolean getVisible(){
        return currState.isVisible();
    }

    public void toggleActive(){
        ColorAdjust inactive = new ColorAdjust(0, 0, .5, 0);
        ColorAdjust active = new ColorAdjust(0, -1, -.5, 0);
        this.isActive = !this.isActive;
        if (this.isActive){
            this.setEffect(active);
        } else this.setEffect(inactive);

    }
}

