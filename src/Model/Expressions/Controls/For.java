package Model.Expressions.Controls;
import Model.Expressions.Basic.Constant;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;

import java.util.Map;

public class For implements Expression, VariableArgumentTaker {

    private String variable;
    private Expression[] inputs;
    private Map<String, Constant> variableMap;

    public For(String variable, Map<String, Constant> variableMap, Expression... inputs){
        if(inputs.length < getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("At least %d Expressions required", getDefaultNumExpressions()));
        }
        this.inputs = inputs;
        this.variableMap = variableMap;
        this.variable = variable;
    }

    @Override
    public double evaluate(){
        double out = 0;
        double start = inputs[0].evaluate();
        double end = inputs[1].evaluate();
        double step = inputs[2].evaluate();
        new Make(variable, variableMap, new Constant(start));
        for(double index = start; start < end ? index < end : index > end; index+=step){
            new Make(variable, variableMap, new Constant(index));
            for(int x = 3; x<inputs.length; x++){
                out = inputs[x].evaluate();
            }
        }
        return out;
    }

    @Override
    public int getDefaultNumExpressions() {
        return 3;
    }

}
