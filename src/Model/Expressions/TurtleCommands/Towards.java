package Model.Expressions.TurtleCommands;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;
import Model.Expressions.Interfaces.TurtleExpression;
import frontend.TurtleState;
import java.util.Deque;

public class Towards implements Expression, ExpressionTaker, TurtleExpression {

    private Expression[] inputs;
    private Deque<TurtleState> queue;

    public Towards(Deque<TurtleState> queue, Expression... inputs) {
        if(inputs.length != getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expressions required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
        this.queue=queue;
    }

    @Override
    public double evaluate() {
        TurtleState copy = new TurtleState(queue.getLast());
        double heading = copy.getAngle();
        double vecX = inputs[0].evaluate() - copy.getX();
        double vecY = inputs[1].evaluate() - copy.getY();
        double newAngle = Math.atan(vecY/vecX); //TODO:FIX
        copy.setAngle(newAngle);
        queue.addLast(copy);
        return Math.abs(newAngle-heading);

    }

    public int getDefaultNumExpressions(){
        return 2;
    }

}

