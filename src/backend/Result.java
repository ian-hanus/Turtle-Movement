package backend;

import java.util.LinkedList;
import java.util.Queue;

public class Result {

    private int returnValue;
    private Queue<Turtle> turtleStates;

    public Result() {
        returnValue = 0;
        turtleStates = new LinkedList<>();
    }

    public void setReturnValue(int value) {
        returnValue = value;
    }

    public int getReturnValue() {
        return returnValue;
    }

    public void addTurtleState(Turtle state) {
        turtleStates.add(state);
    }

    public Queue<Turtle> getTurtleStates() {
        return turtleStates;
    }
}
