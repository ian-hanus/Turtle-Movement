package Model.Expressions.Boolean;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;

public class Not implements Expression, ExpressionTaker{

    private Expression[] inputs;

    public Not(Expression[] inputs)
    {
        if(inputs.length!= getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expression required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
    }


    @Override
    public double evaluate(){
        return inputs[0].evaluate() == 0 ? 1 : 0;
    }

    @Override
    public int getDefaultNumExpressions() {
        return 1;
    }
}
