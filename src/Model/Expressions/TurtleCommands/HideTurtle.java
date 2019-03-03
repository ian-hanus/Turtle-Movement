package Model.Expressions.TurtleCommands;
import Model.Expressions.Expression;

import java.util.Deque;
import frontend.TurtleState;

public class HideTurtle extends Expression {

    private Deque<TurtleState> queue;

    public HideTurtle(Deque<TurtleState> queue) throws AlteringExpressionException
    {
        setArguments(queue);
    }

    public void setArguments(Deque<TurtleState> queue) throws AlteringExpressionException{
        finalizeStates();
        this.queue = queue;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        TurtleState copy = new TurtleState(queue.getLast());
        copy.setVisibility(false);
        queue.push(copy);
        return 0;
    }

    @Override
    public Class[] getArgumentTypes() {
        return new Class[]{java.util.Deque.class};
    }
}
