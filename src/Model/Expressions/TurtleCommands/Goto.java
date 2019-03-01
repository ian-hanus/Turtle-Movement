package Model.Expressions.TurtleCommands;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;
import java.lang.Math;
import java.util.Deque;
import frontend.TurtleState;

public class Goto extends Expression {

    private Expression x;
    private Expression y;

    private Deque<TurtleState> queue;

    public Goto(Expression x, Expression y, Deque<TurtleState> queue) throws AlteringExpressionException
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
        double oldX = copy.getX();
        double oldY = copy.getY();
        double newX = x.evaluate();
        double newY = y.evaluate();
        copy.setX(newX);
        copy.setY(newY);
        queue.push(copy);
        double distance = Math.sqrt(Math.pow(oldX-newX,2)+ Math.pow(oldY-newY,2));
        return distance;
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, expression, java.util.Deque.class};
    }
}
