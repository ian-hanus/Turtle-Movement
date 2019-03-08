package frontend;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.File;
import java.util.*;

public class Canvas extends Pane {

    private final TurtleState STARTING_STATE =  new TurtleState();
    private final Color DEFAULT_PENCOLOR = Color.BLACK;
    private final Color DEFAULT_BACKCOLOR = Color.WHITE;
    private Image turtleImage = new Image(new File("./src/GUIResources/turtle.png").toURI().toString());
    Color penColor;
    Color backgroundColor;
    Double penWidth;
    Map<Integer, TurtleSprite> turtles = new HashMap<>();
    //Set<Line> lines;

    public Canvas(){
        penWidth = 1.5;
        penColor = DEFAULT_PENCOLOR;
        backgroundColor = DEFAULT_BACKCOLOR;
        //lines = new HashSet<>();

        turtles.put(1, new TurtleSprite(STARTING_STATE, turtleImage, this.getHeight(), this.getWidth()));
        this.getChildren().add(turtles.get(1));
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
            TurtleState nextState = states.remove();
//
            if (nextState.isDown()){
                drawLine(turtles.get(1).currState, nextState, penColor);
            }

            turtles.get(1).setTurtle(nextState, getHeight(), getWidth());
            if (nextState.shouldReset()){
                for(Line l : (turtles.get(1)).getLines()){
                    this.getChildren().remove(l);
                }
                turtles.get(1).clearLines();
            }
        }
    }


    private void drawLine (TurtleState start, TurtleState end, Color penColor){
        boolean wrapVert = ((getHeight()/2 + start.getY()))/getHeight() == end.getY()/getHeight();
        boolean wrapHorz = ((getWidth()/2 + start.getX())/getWidth()) == end.getX()/getWidth();
        if (!wrapHorz && !wrapVert){
            Line nextLine = new Line((start.getX() + getWidth()/2) % getWidth(),  (getHeight()/2-start.getY()) % getHeight(), (end.getX() + getWidth()/2) % getWidth(), (getHeight()/2-end.getY()) % getHeight());
            nextLine.setFill(Color.BLACK);//Color.color(start.getPenColor(), start.getPenColor(), 1.0));
            nextLine.setStroke(Color.BLACK);//color(start.getPenColor(), start.getPenColor(), 1.0));
            nextLine.setStrokeWidth(5);//start.getPenSize());

            turtles.get(1).addLine(nextLine);
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
    public void setPenColor(Color color){
        penColor = color;
    }
    public void setPenWidth(Double w){
        penWidth = w;

    }

    public Color getPenColor(){
        return penColor;
    }

    public void setBackgroundColor(Color color){
        backgroundColor = color;
        this.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public List<TurtleSprite> getTurtleList(){
        List<TurtleSprite> origList = new ArrayList<>();
        for (TurtleSprite t : turtles.values()) origList.add(t);
        return Collections.unmodifiableList(origList);
    }
}
