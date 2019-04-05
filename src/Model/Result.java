package Model;

import frontend.TurtleState;

import java.util.Deque;
import java.util.Map;

/**
 * @author Jonathan Yu
 */
public class Result {

    private double returnValue;
    private Deque<TurtleState> turtleStates;
    private Map<String, String> variables;
    private Map<String, String[]> procedures;

    public Result(double val, Deque<TurtleState> states, Map<String, String> vars, Map<String, String[]> procs) {
        returnValue = val;
        turtleStates = states;
        variables = vars;
        procedures = procs;
    }

    /**
     * Gets the return value of the result of the entered command(s)
     * @return the return value
     */
    public double getReturnValue() {
        return returnValue;
    }

    /**
     * Gets the deque of turtle states representing "destinations" as a result of the entered command(s)
     * @return the deque of turtle states
     */
    public Deque<TurtleState> getTurtleStates() {
        return turtleStates;
    }

    /**
     * Gets the mapping of variable names to values
     * @return the map of variables
     */
    public Map<String, String> getVariableMap() {
        return variables;
    }

    /**
     * Gets the mapping of procedure names to values
     * @return the map of procedures
     */
    public Map<String, String[]> getProcedureMap() {
        return procedures;
    }
}
