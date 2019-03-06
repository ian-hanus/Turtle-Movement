package Model.Expressions.Boolean;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;

import java.util.Arrays;

public class And implements Expression, VariableArgumentTaker {

    private Expression[] inputs;

    public And(Expression... inputs) {
        if(inputs.length == 0){
            throw new IllegalArgumentException(String.format("Insufficient Expressions input, at least %d expected", getDefaultNumExpressions()));
        }
        this.inputs = inputs;
    }

    @Override
    public double evaluate() {
        return Arrays.stream(inputs)
                .map(expression -> expression.evaluate() == 0 ? 0 : 1)
                .reduce(1, (a,b) -> a*b);
    }

    @Override
    public int getDefaultNumExpressions(){
        return 2;
    }
}
