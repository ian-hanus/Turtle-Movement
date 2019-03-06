package Model.Expressions.TurtleCommands;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;
import Model.Expressions.Interfaces.VariableArgumentTaker;
import frontend.TurtleState;
import java.util.Arrays;
import java.util.Deque;

public class HideTurtle implements Expression, ExpressionTaker {

    private Expression[] inputs;
    private Deque<TurtleState> queue;

    public HideTurtle(Deque<TurtleState> queue, Expression[] inputs) {
        if(inputs.length != getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expressions required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
        this.queue=queue;
    }

    @Override
    public double evaluate() {
        TurtleState copy = new TurtleState(queue.getLast());
        copy.setVisibility(false);
        queue.addLast(copy);
        return 0;
    }

    public int getDefaultNumExpressions(){
        return 0;
    }
}
