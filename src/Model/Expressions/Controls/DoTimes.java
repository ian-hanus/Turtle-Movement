package Model.Expressions.Controls;
import Model.Expressions.Basic.Constant;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;

import java.util.Map;

public class DoTimes implements Expression, VariableArgumentTaker {

    private String variable;
    private Expression[] inputs;
    private Map<String, Constant> variableMap;

    public DoTimes(String variable, Map<String, Constant> variableMap, Expression... inputs){
        if(inputs.length < getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("At least %d Expressions required", getDefaultNumExpressions()));
        }
        this.inputs = inputs;
        this.variableMap = variableMap;
        this.variable = variable;
    }

    @Override
    public double evaluate(){
        double iterationMaxCount = inputs[0].evaluate();
        new Make(variable, variableMap, new Constant(0));
        double out = 0;
        for(int i = 0; i< iterationMaxCount; i++) {
            int iteration = 1+(int)variableMap.get(variable).evaluate();
            new Make(variable, variableMap, new Constant(iteration));
            for (int x = 1; x < inputs.length; x++) {
                out = inputs[x].evaluate();
            }
        }
        return out;
    }

    @Override
    public int getDefaultNumExpressions() {
        return 1;
    }

}
