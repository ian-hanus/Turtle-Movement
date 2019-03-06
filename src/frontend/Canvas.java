package frontend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.File;
import java.util.*;

public class Canvas extends Pane {
    private final double STARTING_ANGLE = 90;

    private final TurtleState STARTING_STATE =  new TurtleState(0, 0, STARTING_ANGLE, true, true);
    private final Color DEFAULT_PENCOLOR = Color.BLACK;
    private Image turtleImage = new Image(new File("./src/GUIResources/turtle.png").toURI().toString());
    Color penColor;
    Map<Integer, TurtleSprite> turtles = new HashMap<>();
    //Set<Line> lines;

    public Canvas(){
        penColor = DEFAULT_PENCOLOR;
        //lines = new HashSet<>();

        turtles.put(1, new TurtleSprite(STARTING_STATE, this.getHeight(), this.getWidth()));
////        System.out.println(this.getHeight());
////        System.out.println(this.getWidth());
//        activeTurtleImage = turtleImage;
//        turtleSprite.setFitHeight(TURTLE_SIZE);
//        turtleSprite.setFitWidth(TURTLE_SIZE);
//        turtleSprite.setPreserveRatio(true);
//        setTurtle(turtleSprite, currState);
    }







    public void updateCanvas(Deque<TurtleState> states){
        while (!states.isEmpty()){
            TurtleState nextState = states.pop();
            turtles.get(nextState.getID()).setTurtle(nextState, getHeight(), getWidth());
        }
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

    public ImageView getTurtleSprite(int id){
        return turtles.get(id);
    }

    public void setTurtleImage(Image i){
        turtleImage = i;
        for (TurtleSprite t : turtles.values()){
            t.setTurtleImage(i);
        }
    }
}
