package Model.Expressions.TurtleCommands;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;
import Model.Expressions.Interfaces.TurtleExpression;
import frontend.TurtleState;
import java.util.Deque;

public class PenUp implements Expression, ExpressionTaker, TurtleExpression {

    private Expression[] inputs;
    private Deque<TurtleState> queue;

    public PenUp(Deque<TurtleState> queue, Expression... inputs) {
        if(inputs.length != getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expressions required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
        this.queue=queue;
    }

    @Override
    public double evaluate() {
        TurtleState copy = new TurtleState(queue.getLast());
        copy.setDown(false);
        queue.addLast(copy);
        return 1;
    }

    public int getDefaultNumExpressions(){
        return 0;
    }
}
