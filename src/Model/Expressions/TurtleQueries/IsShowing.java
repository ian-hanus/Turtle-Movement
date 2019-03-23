package Model.Expressions.TurtleQueries;

import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.TurtleExpression;
import frontend.TurtleState;

import java.util.Deque;

public class IsShowing implements Expression, TurtleExpression {

    private Deque<TurtleState> queue;

    public IsShowing(Deque<TurtleState> queue) {
        this.queue=queue;
    }

    @Override
    public double evaluate() {
        TurtleState copy = new TurtleState(queue.getLast());
        return copy.isVisible() ? 1 : 0;
    }

    public int getDefaultNumExpressions(){
        return 0;
    }
}
