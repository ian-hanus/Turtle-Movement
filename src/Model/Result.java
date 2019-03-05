package Model;

import frontend.TurtleState;

import java.util.Deque;

public class Result {

    private double returnValue;
    private Deque<TurtleState> turtleStates;

    public Result(double val, Deque<TurtleState> states) {
        returnValue = val;
        turtleStates = states;
    }

    public double getReturnValue() {
        return returnValue;
    }

    public Deque<TurtleState> getTurtleStates() {
        return turtleStates;
    }
}
