package Model.Expressions.Boolean;
import Model.Expressions.Expression;

import java.util.Arrays;

public class And implements Expression{

    private Expression[] inputs;

    public And(Expression... inputs)
    {
        if(inputs.length == 0){
            throw new IllegalArgumentException("Insufficient Expressions input");
        }
        this.inputs = inputs;
    }

    @Override
    public double evaluate() {
        return Arrays.stream(inputs)
                .map(expression -> expression.evaluate() == 0 ? 0 : 1)
                .reduce(1, (a,b) -> a*b);
    }
}
