package frontend;


import javafx.animation.PathTransition;
import javafx.geometry.Bounds;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;
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
    private final double INACTIVE_BRIGHTNESS = -.5;
    private final double ACTIVE_BRIGHTNESS = .5;

    public TurtleSprite(){
        super();
        this.setFitHeight(TURTLE_SIZE);
        this.setFitWidth(TURTLE_SIZE);
        this.setPreserveRatio(true);
        this.setOnMouseClicked((MouseEvent e) -> this.toggleActive());
        this.myLines = new ArrayList<>();
    }
    public TurtleSprite(Image image){
        super();
        this.setTurtleImage(image);
        this.setFitHeight(TURTLE_SIZE);
        this.setFitWidth(TURTLE_SIZE);
        this.setPreserveRatio(true);
        this.setOnMouseClicked((MouseEvent e) -> this.toggleActive());
        this.myLines = new ArrayList<>();
    }

    public TurtleSprite(TurtleState ts, Image i, double h, double w){
        super();
        this.setFitHeight(TURTLE_SIZE);
        this.setFitWidth(TURTLE_SIZE);
        this.setPreserveRatio(true);
        this.setTurtleImage(i);
        this.placeTurtle(ts, h, w);
        this.myLines = new ArrayList<>();
        this.setOnMouseClicked((MouseEvent e) -> this.toggleActive());

        currState = ts;
        if (currState.isVisible()){
            //setTurtleImage(activeImage);
        } else {
            this.setImage(null);
        }
    }

    public void clearLines(){
        myLines.clear();
    }
    public void placeTurtle(TurtleState ts, double h, double w){
        this.setX((ts.getX() + w/2 - TURTLE_SIZE/2) % w);
        this.setY((-ts.getY() + h/2 - TURTLE_SIZE/2 ) % h);
        this.setRotate(ts.getAngle()-90);
        this.currState = ts;
        System.out.println(this.currState.getX());
        System.out.println(this.currState.getY());
        System.out.println(this.currState.getAngle());
    }
    public void setTurtle(TurtleState ts, double h, double w){
        Path path = new Path();
        path.getElements().add(new MoveTo(this.currState.getX(), this.currState.getY()));
        path.getElements().add(new LineTo(ts.getX(), ts.getY()));
        PathTransition transition = new PathTransition(new Duration(1000), path);
        placeTurtle(ts, h, w);
        if (currState.isVisible()){
            setTurtleImage(activeImage);
        } else {
            this.setImage(null);
        }
        //System.out.println(transition);
        transition.play();
    }

    public void setTurtleImage(Image i) {


//        System.out.println(this.getX());
//        System.out.println(this.getY());

        this.setImage(i);
        activeImage = i;

    }

    public int getID(){
        return currState.getID();
    }
    public boolean isPenDown(){
        return currState.isDown();
    }
    public boolean isVisibility(){
        return currState.isVisible();
    }

    public void toggleActive(){
        ColorAdjust inactive = new ColorAdjust(0, 0, INACTIVE_BRIGHTNESS, 0);
        ColorAdjust active = new ColorAdjust(0, -1, ACTIVE_BRIGHTNESS, 0);
        this.isActive = !this.isActive;
        if (this.isActive){
            this.setEffect(active);
        } else this.setEffect(inactive);

    }
    public double getAngle(){
        return this.currState.getAngle();
    }
<<<<<<< HEAD
    public List<Line> getLines(){
        return this.myLines;
    }
    public void addLine(Line l){
        this.myLines.add(l);
=======
    public List<Line> getMyLines(){
        return myLines;
>>>>>>> 2379c8806de4a675ada1f9e1e9f2822a28eacb4b
    }
}

