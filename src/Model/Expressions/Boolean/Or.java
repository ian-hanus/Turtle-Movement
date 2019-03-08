package Model.Expressions.Boolean;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;

import java.util.Arrays;

public class Or implements Expression, VariableArgumentTaker {

    private Expression[] inputs;

    public Or(Expression... inputs) {
        if(inputs.length == 0){
            throw new IllegalArgumentException(String.format("Insufficient Expressions input, at least %d expected", getDefaultNumExpressions()));
        }
        this.inputs = inputs;
    }

    @Override
    public double evaluate() {
        boolean result = Arrays.stream(inputs)
                .map(expression -> expression.evaluate())
                .anyMatch(in -> in!=0);
        return result ? 1 : 0;
    }
    public int getDefaultNumExpressions(){
        return 2;
    }
}
