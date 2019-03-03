package Model.Expressions.TurtleCommands;
import Model.Expressions.Expression;

import java.lang.Math;
import java.util.Deque;
import frontend.TurtleState;

public class Forward extends Expression {

    private Expression distance;
    private Deque<TurtleState> queue;

    public Forward(Expression distance, Deque<TurtleState> queue) throws AlteringExpressionException
    {
        setArguments(distance, queue);
    }

    public void setArguments(Expression distance, Deque<TurtleState> queue) throws AlteringExpressionException{
        finalizeStates();
        this.distance = distance;
        this.queue = queue;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        double distanceAmount = distance.evaluate();
        TurtleState copy = new TurtleState(queue.getLast());
        double heading = copy.getAngle()*Math.PI/180;
        double newX = copy.getX()+distanceAmount*Math.cos(heading);
        double newY= copy.getY()+distanceAmount*Math.sin(heading);
        copy.setX(newX);
        copy.setX(newY);
        queue.push(copy);
        return distanceAmount;

    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, java.util.Deque.class};
    }
}
