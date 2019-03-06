package Model.Expressions.Math;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;

public class Pi implements Expression, ExpressionTaker{

    public Pi(Expression[] inputs)
    {
        if(inputs.length!= getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expressions required", getDefaultNumExpressions()));
        }
    }

    @Override
    public double evaluate(){
        return Math.PI;
    }

    @Override
    public int getDefaultNumExpressions() {
        return 1;
    }
}
