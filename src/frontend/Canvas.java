package frontend;

import backend.Turtle;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Canvas extends Pane {
    private final int STARTING_ANGLE = 90;
    private final double TURTLE_SIZE = 30;
    Color penColor;
    ImageView turtleSprite = new ImageView();
    Image activeTurtleImage;
    TurtleState currState;
    //Set<Line> lines;

    public Canvas(Image turtleImage, Color pencolor){
        penColor = pencolor;
        //lines = new HashSet<>();
        currState = new TurtleState(0, 0, STARTING_ANGLE, true, true);
//        System.out.println(this.getHeight());
//        System.out.println(this.getWidth());
        activeTurtleImage = turtleImage;
        turtleSprite.setFitHeight(TURTLE_SIZE);
        turtleSprite.setFitWidth(TURTLE_SIZE);
        turtleSprite.setPreserveRatio(true);
        setTurtle(turtleSprite, currState);
    }

    private void setTurtle(ImageView sprite, TurtleState ts){
        if (currState.isVisible()){
            setTurtleImage(sprite, activeTurtleImage);
        } else {
            turtleSprite.setImage(null);
        }
        sprite.setX((currState.getX() + getWidth()/2) % getWidth());
        sprite.setY((-currState.getY() + getHeight()/2 ) % getHeight());
        sprite.setRotate(currState.getAngle());
    }

    public void setTurtleImage(ImageView sprite, Image i) {
        if(!this.getChildren().contains(sprite)){
            this.getChildren().add(sprite);
        }
        sprite.setImage(i);
        activeTurtleImage = i;
    }


    public void updateCanvas(List<TurtleState> states){
        while (!states.isEmpty()){
            if(currState.isDown()){
                drawLine(currState, states.get(0), penColor);
            }
            currState = states.remove(0);
        }
        setTurtle(turtleSprite, currState);

    }


    private void drawLine (TurtleState start, TurtleState end, Color penColor){
        //System.out.println("DRAWING");
        boolean wrapVert = ((getHeight()/2 + start.getY()))/getHeight() == end.getY()/getHeight();
        boolean wrapHorz = ((getWidth()/2 + start.getX())/getWidth()) == end.getX()/getWidth();
        if (!wrapHorz && !wrapVert){
//            System.out.println(String.format("START: %d, %d", start.getX(), start.getY() ));
//            System.out.println(String.format("END: %d, %d", end.getX(), end.getY() ));
            Line nextLine = new Line((start.getX() + getWidth()/2) % getWidth(),  (getHeight()/2-start.getY()) % getHeight(), (end.getX() + getWidth()/2) % getWidth(), (getHeight()/2-end.getY()) % getHeight());
            nextLine.setFill(penColor);

            this.getChildren().add(nextLine);
        }
    }

    public ImageView getTurtleSprite(){
        return turtleSprite;
    }

    public void setPenColor(Color color){
        penColor = color;
    }
}
