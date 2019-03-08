package Model.Expressions.Controls;
import Model.Expressions.Basic.Constant;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;
import Model.Expressions.Interfaces.VariableArgumentTaker;

import java.util.Map;

public class IfElse implements Expression, ExpressionTaker{

    private Expression[] inputs;
    private Expression[] trueBranch;
    private Expression[] falseBranch;

    public IfElse(Expression[] trueBranch, Expression[] falseBranch, Expression... inputs){
        if(inputs.length != getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expression required", getDefaultNumExpressions()));
        }
        this.inputs = inputs;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    @Override
    public double evaluate(){
        double out = 0;
        Expression[] executed = inputs[0].evaluate() != 0 ? trueBranch : falseBranch;
        for(Expression expression: executed){
            out = expression.evaluate();
        }
        return out;
    }

    @Override
    public int getDefaultNumExpressions() {
        return 1;
    }

}
