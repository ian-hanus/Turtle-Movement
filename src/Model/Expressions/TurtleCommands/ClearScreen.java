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
        double oldX = copy.getX();
        double oldY = copy.getY();
        double newX = 0;
        double newY = 0;
        copy.setX(newX);
        copy.setY(newY);
        copy.setShouldReset(true);
        copy.setAngle(90);
        queue.addLast(copy);
        double distance = Math.sqrt(Math.pow(oldX-newX,2)+ Math.pow(oldY-newY,2));
        return distance;
    }

    public int getDefaultNumExpressions(){
        return 0;
    }

}








