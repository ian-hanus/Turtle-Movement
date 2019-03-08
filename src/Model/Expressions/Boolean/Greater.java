package Model.Expressions.Boolean;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;

public class Greater implements Expression, ExpressionTaker{

    private Expression[] inputs;

    public Greater(Expression... inputs) {
        if(inputs.length != getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expression required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;

    }

    @Override
    public double evaluate() {
        return inputs[0].evaluate() > inputs[1].evaluate() ? 1 : 0;
    }

    public int getDefaultNumExpressions(){
        return 2;
    }


}