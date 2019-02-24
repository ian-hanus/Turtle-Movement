package frontend;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.List;

public class Canvas extends HBox {
    private final int STARTING_ANGLE = 90;
    Config myConfig;
    ImageView turtleSprite = new ImageView();
    TurtleState currState = new TurtleState(0, 0, STARTING_ANGLE, true, true);




    private void updateConfigVars(){
        this.setBackground(new Background(new BackgroundFill(myConfig.getBackgroundColor(), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void udpateCanvas(List<TurtleState> states){
        while (!states.isEmpty()){
            if(currState.isDown()){
                drawLine(currState, states.get(0));
            }
            currState = states.remove(0);
        }
        turtleSprite.setX((currState.getX() + getWidth()/2) % getWidth());
        turtleSprite.setY((currState.getY() + getHeight()/2 ) % getHeight());
        turtleSprite.setRotate(currState.getAngle());
        if (!currState.isVisible()){
            turtleSprite.setImage(null);
        } else {
            turtleSprite.setImage(myConfig.getTurtleImage());
        }

    }

    private void executeStep(TurtleState start, TurtleState end){

    }

    private void drawLine (TurtleState start, TurtleState end){
        boolean wrapVert = (getHeight()/2 + (start.getY())) / getHeight() == end.getY()/getHeight();
        boolean wrapHorz = (getWidth()/2 + start.getX()/getWidth()) == end.getX()/getWidth();
        if (!wrapHorz && !wrapVert){

        }


    }


}
