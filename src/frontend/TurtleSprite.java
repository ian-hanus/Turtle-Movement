package frontend;


import javafx.animation.PathTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class TurtleSprite extends ImageView {
    /**
     * gets the ID of the current sprite
     * @return the ID of the current turtle
     */
    public int getNum() {
        return num;
    }

    TurtleState currState;
    private int num;
    private boolean isActive;
    Image activeImage;
    private List<Line> myLines;
    private final double TURTLE_SIZE = 30;
    private final double INACTIVE_BRIGHTNESS = -.5;
    private final double ACTIVE_BRIGHTNESS = .5;

    /**
     * default constructor to ensure that the sprite renders correctly graphically
     */
    public TurtleSprite(){
        super();
        this.setFitHeight(TURTLE_SIZE);
        this.setFitWidth(TURTLE_SIZE);
        this.setPreserveRatio(true);
        this.setOnMouseClicked((MouseEvent e) -> this.toggleActive());
        this.myLines = new ArrayList<>();
    }

    /**
     * Constructor used to initialize a new turtlesprite at the center of the window
     * @param ts the starting state of the new turtlesprite
     * @param i its starting image
     * @param h height of the window
     * @param w width of the window
     */
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
            setTurtleImage(activeImage);
        } else {
            this.setImage(null);
        }
    }

    /**
     * removes the trails that the turtlesprite has left to this point
     */
    public void clearLines(){
        myLines.clear();
    }

    /**
     * sets only the location of the sprite on the canvas given a turtlestate
     * @param ts the new state associated with the turtle
     * @param h height of the canvas window
     * @param w width of the canvas window
     */
    public void placeTurtle(TurtleState ts, double h, double w){
        //System.out.println((ts.getX() + w/2 - TURTLE_SIZE/2) % w);
        System.out.println((-ts.getY() + h/2 - TURTLE_SIZE/2 ) % +h);
        System.out.println((-ts.getY() + h/2 - TURTLE_SIZE/2 ) % -h);
        this.setX((ts.getX() + w/2 - TURTLE_SIZE/2)%w <0 ? ((ts.getX() + w/2 - TURTLE_SIZE/2) % w) : (ts.getX() + w/2 - TURTLE_SIZE/2) % -w) ;
        this.setY( ((-ts.getY() + h/2 - TURTLE_SIZE/2 ) % h) < 0 ? (-ts.getY() + h/2 - TURTLE_SIZE/2 ) % h : (-ts.getY() + h/2 - TURTLE_SIZE/2 ) % -h);
        this.setRotate(ts.getAngle()-90);
        this.currState = ts;
//        System.out.println(this.currState.getX());
//        System.out.println(this.currState.getY());
//        System.out.println(this.currState.getAngle());
    }

    /**
     * sets the other parts of the turtlesprite given the state
     * @param ts state from which to read
     * @param h height of canvas window
     * @param w width of canvas window
     */
    public void setTurtle(TurtleState ts, double h, double w){
        Path path = new Path();
        if(currState.getX() != ts.getX() || currState.getY() != ts.getY()) {
            System.out.println(w);
            System.out.println(h);
            path.getElements().add(new MoveTo(((currState.getX() + w / 2) % w), ((-currState.getY() + h / 2) % h)));
            path.getElements().add(new LineTo(((ts.getX() + w / 2) % w), ((-ts.getY() + h / 2) % -h)));
            PathTransition transition = new PathTransition(new Duration(200), path);
            transition.setNode(this);
            transition.play();
        }
        placeTurtle(ts, h, w);
        if (currState.isVisible()){
            setTurtleImage(activeImage);
        } else {
            this.setImage(null);
        }
    }

    /**
     * used by canvas class to change the image of the specified turtlesprite
     * @param i image to make the sprite display
     */
    public void setTurtleImage(Image i) {

        this.setImage(i);
        activeImage = i;

    }

    /**
     * gets the ID of this sprite
     * @return the ID
     */
    public double getID(){
        return currState.getID();
    }

    /**
     * tells us whether the current sprite has its pen down or up
     * @return the pen state
     */
    public boolean isPenDown(){
        return currState.isDown();
    }

    /**
     * tells us whether the current sprite should be visible
     * @return the visibility
     */
    public boolean isVisibility(){
        return currState.isVisible();
    }

    /**
     * changes the appearance and active state of the turtle
     */
    public void toggleActive(){
        ColorAdjust inactive = new ColorAdjust(0, 0, INACTIVE_BRIGHTNESS, 0);
        ColorAdjust active = new ColorAdjust(0, -1, ACTIVE_BRIGHTNESS, 0);
        this.isActive = !this.isActive;
        if (this.isActive){
            this.setEffect(active);
        } else this.setEffect(inactive);

    }

    /**
     * get the angle of the sprite
     * @return the angle of the sprite
     */
    public double getAngle(){
        return this.currState.getAngle();
    }

    /**
     * allows the canvas to selectively remove lines from the canvas
     * @return the list of lines associated with this sprite
     */
    public List<Line> getLines(){
        return this.myLines;
    }

    /**
     * adds a line to the list with this sprite
     * @param l the new line
     */
    public void addLine(Line l){
        this.myLines.add(l);

}

    /**
     * gies back all of the information currently associated with this sprite
     * NOTE: this woudl make a lot of the other methods her obsolete
     * @return the data structure holding the sprites information
     */
    public TurtleState getCurrState(){
        return new TurtleState(currState);
    }

    /**
     * tells other modules whether this sprite is active
     * @return whether it's active
     */
    public boolean isActive() {
        return isActive;
    }
}

