package Model.Expressions.TurtleQueries;

import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.TurtleExpression;
import frontend.TurtleState;

import java.util.Deque;

public class IsPenDown implements Expression, TurtleExpression {

    private Expression[] inputs;
    private Deque<TurtleState> queue;

    public IsPenDown(Deque<TurtleState> queue) {
        this.queue=queue;
    }

    @Override
    public double evaluate() {
        TurtleState copy = new TurtleState(queue.getLast());
        return copy.isDown() ? 1 : 0;
    }
}
