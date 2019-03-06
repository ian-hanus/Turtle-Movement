package Model.Expressions.TurtleCommands;
import Model.Expressions.Interfaces.Expression;

import java.lang.Math;
import java.util.Deque;
import frontend.TurtleState;

public class Home extends Expression {

    private Deque<TurtleState> queue;

    public Home(Deque<TurtleState> queue) throws AlteringExpressionException
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
        double oldX = copy.getX();
        double oldY = copy.getY();
        double newX = 0;
        double newY = 0;
        copy.setX(newX);
        copy.setY(newY);
        queue.push(copy);
        double distance = Math.sqrt(Math.pow(oldX-newX,2)+ Math.pow(oldY-newY,2));
        return distance;
    }

    @Override
    public Class[] getArgumentTypes() {
        return new Class[]{java.util.Deque.class};
    }
}
