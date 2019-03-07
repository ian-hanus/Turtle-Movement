package Model.Expressions.TurtleCommands;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;
import java.lang.Math;
import java.util.Deque;
import frontend.TurtleState;

public class SetHeading extends Expression {

    private Expression rotation;
    private Deque<TurtleState> queue;

    public SetHeading(Expression distance, Deque<TurtleState> queue) throws AlteringExpressionException
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
        copy.setAngle(distanceAmount);
        queue.addLast(copy);
        return Math.abs(distanceAmount-heading);
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, java.util.Deque.class};
    }
}
