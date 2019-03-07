package Model.Expressions.Controls;
import Model.Expressions.Basic.Constant;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;

import java.util.Map;

public class Make implements Expression, ExpressionTaker {

    private String name;
    private Expression value;
    private Map<String, Constant> variableMap;

    public Make(String name, Map<String, Constant> variableMap, Expression... inputs){
        if(inputs.length != getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expressions required", getDefaultNumExpressions()));
        }

        this.value = inputs[0];
        this.name = name;
        this.variableMap = variableMap;
    }

    @Override
    public double evaluate(){
        double val = value.evaluate();
        variableMap.put(name, new Constant(val));
        return val;
    }

    @Override
    public int getDefaultNumExpressions() {
        return 1;
    }

}
