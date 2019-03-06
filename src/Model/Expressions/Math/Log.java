package Model.Expressions.Math;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;

public class Log implements Expression, ExpressionTaker{

    private Expression[] inputs;

    public Log(Expression[] inputs)
    {
        if(inputs.length!= getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expression required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
    }

    @Override
    public double evaluate(){
        return Math.log(inputs[0].evaluate());
    }

    @Override
    public int getDefaultNumExpressions() {
        return 1;
    }
}
