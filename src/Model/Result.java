package Model;

import frontend.TurtleState;

import java.util.Deque;
import java.util.Map;

public class Result {

    private double returnValue;
    private Deque<TurtleState> turtleStates;
    private Map<String, Double> variables;
    private Map<String, String[]> procedures;

    public Result(double val, Deque<TurtleState> states, Map<String, Double> vars, Map<String, String[]> procs) {
        returnValue = val;
        turtleStates = states;
        variables = vars;
        procedures = procs;
    }

    public double getReturnValue() {
        return returnValue;
    }

    public Deque<TurtleState> getTurtleStates() {
        return turtleStates;
    }

    public Map<String, Double> getVariableMap() {
        return variables;
    }

    public Map<String, String[]> getProcedureMap() {
        return procedures;
    }
}
