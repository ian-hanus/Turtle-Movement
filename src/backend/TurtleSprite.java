package backend;

import frontend.TurtleState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.util.List;


public class TurtleSprite extends ImageView {
    private List<Line> myLines;
    private final double TURTLE_SIZE = 30;
    private boolean isActive;
    TurtleState currState;
    Image activeImage;

    public TurtleSprite(){
        super();
        this.setFitHeight(TURTLE_SIZE);
        this.setFitWidth(TURTLE_SIZE);
        this.setPreserveRatio(true);
    }

    public void clearLines(){
        myLines.clear();
    }

    public void setTurtle(TurtleState ts, double h, double w){
        if (currState.isVisible()){
            setTurtleImage(activeImage);
        } else {
            this.setImage(null);
        }
        this.setX((currState.getX() + w/2) % w);
        this.setY((-currState.getY() + h/2 ) % h);
        this.setRotate(currState.getAngle());
    }

    public void setTurtleImage(Image i) {
        this.setImage(i);
        activeImage = i;
    }
}
