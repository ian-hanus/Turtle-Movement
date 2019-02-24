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
            executeStep(states.get(0), currState);
            currState = states.remove(0);
        }
        turtleSprite.setX(currState.getX()%getWidth());
        turtleSprite.setY(currState.getY()%getHeight());
        turtleSprite.setRotate(currState.getAngle());
        if (!currState.isVisible()){
            turtleSprite.setImage(null);
        } else {
            turtleSprite.setImage(myConfig.getTurtleImage());
        }

    }

    private void executeStep(TurtleState curr, TurtleState prev){

    }

    private void drawLine (TurtleState curr, TurtleState prev){

    }


}
