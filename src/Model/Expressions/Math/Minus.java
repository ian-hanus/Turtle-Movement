package Model.Expressions.Math;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;

public class Minus implements Expression, ExpressionTaker{

    private Expression[] inputs;

    public Minus(Expression[] inputs)
    {
        if(inputs.length!= getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expressions required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
    }

    @Override
    public double evaluate(){
        return -inputs[0].evaluate();
    }

    @Override
    public int getDefaultNumExpressions() {
        return 1;
    }
}
