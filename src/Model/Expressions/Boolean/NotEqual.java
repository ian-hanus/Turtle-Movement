package Model.Expressions.Boolean;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class NotEqual implements Expression, VariableArgumentTaker {

    private Expression[] inputs;

    public NotEqual(Expression... inputs) {
        if(inputs.length == 0){
            throw new IllegalArgumentException(String.format("Insufficient Expressions input, at least %d expected", getDefaultNumExpressions()));
        }
        this.inputs = inputs;
    }

    @Override
    public double evaluate() {
        Set<Double> results = Arrays.stream(inputs)
                .map(expression -> expression.evaluate())
                .collect(Collectors.toSet());
        return results.size()==inputs.length ? 0 : 1;
    }
    public int getDefaultNumExpressions(){
        return 2;
    }
}
