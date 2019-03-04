package frontend;

import backend.Turtle;
import backend.TurtleSprite;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.File;
import java.util.*;

public class Canvas extends Pane {
    private final int STARTING_ANGLE = 90;

    private final TurtleState STARTING_STATE =  new TurtleState(0, 0, STARTING_ANGLE, true, true);
    private final Color DEFAULT_PENCOLOR = Color.BLACK;
    private final Image DEFAULT_IMAGE = new Image(new File("./src/GUIResources/turtle.png").toURI().toString());
    Color penColor;
    Map<Integer, TurtleSprite> turtles = new HashMap<>();
    //Set<Line> lines;

    public Canvas(){
        penColor = DEFAULT_PENCOLOR;
        //lines = new HashSet<>();
        turtles.put(1, new TurtleSprite());
        turtles.get(1).setTurtle(STARTING_STATE, this.getHeight(), this.getWidth());

//        System.out.println(this.getHeight());
//        System.out.println(this.getWidth());


    }

   public void setTurtle(int id, TurtleState ts){

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
            System.out.println(String.format("START: %d, %d", start.getX(), start.getY() ));
            System.out.println(String.format("END: %d, %d", end.getX(), end.getY() ));
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
