package Model.Expressions.TurtleCommands;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;
import frontend.TurtleState;
import java.util.Deque;

public class SetHeading implements Expression, ExpressionTaker {

    private Expression[] inputs;
    private Deque<TurtleState> queue;

    public SetHeading(Deque<TurtleState> queue, Expression[] inputs) {
        if(inputs.length != getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expression required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
        this.queue=queue;
    }

    @Override
    public double evaluate() {

        double distanceAmount = inputs[0].evaluate();
        TurtleState copy = new TurtleState(queue.getLast());
        double heading = copy.getAngle();
        copy.setAngle(distanceAmount);
        queue.addLast(copy);
        return Math.abs(distanceAmount-heading);
    }

    public int getDefaultNumExpressions(){
        return 1;
    }

}
