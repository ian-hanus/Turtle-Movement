package Model.Expressions.TurtleQueries;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;
import frontend.TurtleState;
import java.util.Deque;

public class Heading implements Expression, ExpressionTaker {

    private Expression[] inputs;
    private Deque<TurtleState> queue;

    public Heading(Deque<TurtleState> queue, Expression[] inputs) {
        if(inputs.length != getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expressions required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
        this.queue=queue;
    }

    @Override
    public double evaluate() {
        TurtleState copy = new TurtleState(queue.getLast());
        return copy.getAngle();
    }

    public int getDefaultNumExpressions(){
        return 0;
    }
}
