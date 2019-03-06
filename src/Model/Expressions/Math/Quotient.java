package Model.Expressions.Math;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;

public class Quotient implements Expression, ExpressionTaker{

    private Expression[] inputs;

    public Quotient(Expression[] inputs) {
        if(inputs.length != getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expression required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
    }

    @Override
    public double evaluate() {
        return inputs[0].evaluate()/inputs[1].evaluate();
    }

    public int getDefaultNumExpressions(){
        return 2;
    }

}




