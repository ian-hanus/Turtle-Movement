package Model.Expressions.TurtleCommands;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;
import frontend.TurtleState;
import java.util.Arrays;
import java.util.Deque;

public class Forward implements Expression, VariableArgumentTaker {

    private Expression[] inputs;
    private Deque<TurtleState> queue;

    public Forward(Deque<TurtleState> queue, Expression[] inputs) {
        if(inputs.length < getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("At least %d Expression required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
        this.queue=queue;
    }

    @Override
    public double evaluate() {
        double distance = Arrays.stream(inputs)
                .map(expression -> expression.evaluate())
                .reduce(0.0, (a,b) -> a+b);
        TurtleState copy = new TurtleState(queue.getLast());
        double heading = copy.getAngle()*Math.PI/180;
        double newX = copy.getX()+distance*Math.cos(heading);
        double newY= copy.getY()+distance*Math.sin(heading);
        copy.setX(newX);
        copy.setY(newY);
        queue.addLast(copy);
        return distance;
    }

    public int getDefaultNumExpressions(){
        return 1;
    }

}




