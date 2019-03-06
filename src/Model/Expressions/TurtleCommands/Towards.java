package Model.Expressions.TurtleCommands;
import Model.Expressions.Interfaces.Expression;

import java.lang.Math;
import java.util.Deque;
import frontend.TurtleState;

public class Towards extends Expression {

    private Expression x;
    private Expression y;

    private Deque<TurtleState> queue;

    public Towards(Expression x, Expression y, Deque<TurtleState> queue) throws AlteringExpressionException
    {
        setArguments(x,y,queue);
    }

    public void setArguments(Expression x, Expression y, Deque<TurtleState> queue) throws AlteringExpressionException{
        finalizeStates();
        this.x = x;
        this.y = y;
        this.queue = queue;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        TurtleState copy = new TurtleState(queue.getLast());
        double heading = copy.getAngle();
        double vecX = x.evaluate() - copy.getX();
        double vecY = y.evaluate() - copy.getY();
        double newAngle = Math.atan(vecY/vecX); //TODO:FIX
        copy.setAngle(newAngle);
        queue.push(copy);
        return Math.abs(newAngle-heading);
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, expression, java.util.Deque.class};
    }
}
