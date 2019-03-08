package Model.Expressions.Math;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;

import java.util.Arrays;

public class Product implements Expression, VariableArgumentTaker {

    private Expression[] inputs;

    public Product(Expression... inputs) {
        if(inputs.length < getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("At least %d Expression required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
    }

    @Override
    public double evaluate() {
        return Arrays.stream(inputs)
                .map(expression -> expression.evaluate())
                .reduce(1.0,(a,b) -> a*b);
    }

    public int getDefaultNumExpressions(){
        return 2;
    }

}




