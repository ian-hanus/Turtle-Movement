package Model.Expressions.Math;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;

import java.util.Arrays;

public class Sum implements Expression, VariableArgumentTaker {

    private Expression[] inputs;

    public Sum(Expression... inputs) {
        if(inputs.length < getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("At least %d Expression required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
    }

    @Override
    public double evaluate() {
        return Arrays.stream(inputs)
                .map(expression -> expression.evaluate())
                .reduce(0.0, (a,b) -> a+b);
    }

    public int getDefaultNumExpressions(){
        return 2;
    }

}




