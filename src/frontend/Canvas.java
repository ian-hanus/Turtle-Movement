package frontend;

import Model.Parser;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.awt.event.KeyEvent;
import java.io.File;
import java.security.Key;
import java.util.*;

public class Canvas extends Pane {

    private final TurtleState STARTING_STATE =  new TurtleState();
    private final Color DEFAULT_PENCOLOR = Color.BLACK;
    private final Color DEFAULT_BACKCOLOR = Color.WHITE;
    private Image turtleImage = new Image(new File("./src/GUIResources/turtle.png").toURI().toString());
    private View myView;
    private Terminal myTerminal;
    private List<TurtleSprite> activeTurtles;
    Color penColor;
    Color backgroundColor;
    Double penWidth;
    Map<Integer, TurtleSprite> turtles = new HashMap<>();
    //Set<Line> lines;

    public Canvas(){
        penWidth = 1.5;
        penColor = DEFAULT_PENCOLOR;
        backgroundColor = DEFAULT_BACKCOLOR;
        turtles.put(1, new TurtleSprite(STARTING_STATE, turtleImage, this.getHeight(), this.getWidth()));
        this.getChildren().add(turtles.get(1));
        activeTurtles = new ArrayList<>();
    }

    public Canvas(View v, Terminal t){
        this();
        myView = v;
        myTerminal = t;
        this.setOnKeyPressed(e -> this.handleKeyPress(e.getCode()));
        this.setOnMouseClicked(e -> requestFocus());
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
            nextLine.setStrokeWidth(1);//start.getPenSize());

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

    public void setPenState(boolean state){
        for (TurtleSprite t : turtles.values()){
            t.currState.setDown(state);
        }
    }

    private void handleKeyPress(KeyCode k){
        StringBuilder sb = new StringBuilder();
        //sb.append("tell [");
        updateActiveTurtles();
        for (TurtleSprite t : activeTurtles){
            //sb.append((Math.floor(t.getID())));
        }
        //sb.append("] \n");

        if (k == KeyCode.UP) {
            sb.append("fd 10");
        } else if (k == KeyCode.DOWN) {
            sb.append("bk 10");
        } else if (k == KeyCode.RIGHT) {
            sb.append("rt 15");
        } else if (k == KeyCode.LEFT) {
            sb.append("lt 15");
        } else return;
        myTerminal.getTextArea().setText(myTerminal.getTextArea().getText() + "\n" + sb.toString());
        myView.runCommands();
        this.requestFocus();
    }
    private void updateActiveTurtles(){
        activeTurtles.clear();
        for (TurtleSprite t : turtles.values()){
            if (t.isActive()) activeTurtles.add(t);
        }
    }
}
