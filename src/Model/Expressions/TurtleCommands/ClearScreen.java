package Model.Expressions.TurtleCommands;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;
import Model.Expressions.Interfaces.TurtleExpression;
import frontend.TurtleState;
import java.util.Deque;

public class ClearScreen implements Expression, ExpressionTaker, TurtleExpression {

    private Expression[] inputs;
    Deque<TurtleState> queue;

    public ClearScreen(Deque<TurtleState> queue, Expression... inputs) {
        if(inputs.length != getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expressions required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
        this.queue=queue;
    }

    @Override
    public double evaluate() {
        TurtleState copy = new TurtleState(queue.getLast());
        copy.setShouldReset(true);
        queue.addLast(copy);
        return 1;
    }

    public int getDefaultNumExpressions(){
        return 0;
    }

}








