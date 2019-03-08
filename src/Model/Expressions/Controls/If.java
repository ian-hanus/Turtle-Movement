package Model.Expressions.Controls;
import Model.Expressions.Basic.Constant;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;

import java.util.Map;

public class If implements Expression, VariableArgumentTaker {

    private Expression[] inputs;

    public If(String variable, Map<String, Constant> variableMap, Expression... inputs){
        if(inputs.length < getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("At least %d Expressions required", getDefaultNumExpressions()));
        }
        this.inputs = inputs;
    }

    @Override
    public double evaluate(){
        double out = 0;
        if(inputs[0].evaluate()!=0){
            for(int x=1;x<inputs.length;x++){
                out=inputs[x].evaluate();
            }
        }
        return out;
    }

    @Override
    public int getDefaultNumExpressions() {
        return 1;
    }

}
