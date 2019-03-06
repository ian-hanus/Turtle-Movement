package Model.Expressions.TurtleCommands;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;
import frontend.TurtleState;
import java.util.Arrays;
import java.util.Deque;

public class Goto implements Expression, ExpressionTaker {

    private Expression[] inputs;
    private Deque<TurtleState> queue;

    public Goto(Deque<TurtleState> queue, Expression[] inputs) {
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
        double newX = inputs[0].evaluate();
        double newY = inputs[1].evaluate();
        copy.setX(newX);
        copy.setY(newY);
        queue.addLast(copy);
        double distance = Math.sqrt(Math.pow(oldX-newX,2)+ Math.pow(oldY-newY,2));
        return distance;

    }

    public int getDefaultNumExpressions(){
        return 2;
    }

}




