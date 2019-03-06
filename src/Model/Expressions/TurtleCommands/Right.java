package Model.Expressions.TurtleCommands;
import Model.Expressions.Interfaces.Expression;

import java.util.Deque;
import frontend.TurtleState;

public class Right extends Expression {

    private Expression rotation;
    private Deque<TurtleState> queue;

    public Right(Expression distance, Deque<TurtleState> queue) throws AlteringExpressionException
    {
        setArguments(distance, queue);
    }

    public void setArguments(Expression rotation, Deque<TurtleState> queue) throws AlteringExpressionException{
        finalizeStates();
        this.rotation = rotation;
        this.queue = queue;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        double distanceAmount = rotation.evaluate();
        TurtleState copy = new TurtleState(queue.getLast());
        double heading = copy.getAngle();
        copy.setAngle(heading+distanceAmount);
        queue.push(copy);
        return distanceAmount;
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, java.util.Deque.class};
    }
}
