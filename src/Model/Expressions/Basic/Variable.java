package Model.Expressions.Basic;

import Model.Expressions.Expression;

import java.util.Map;

public class Variable extends Expression{


    private String name;
    private Map<String, Double> variableMap;

    public Variable(String name, Map<String, Double> map) throws AlteringExpressionException{
        setArguments(name,map);
    }


    public void setArguments(String name, Map<String, Double> map) throws AlteringExpressionException {
        finalizeStates();
        this.name = name;
        this.variableMap = map;
    }

    @Override
    public double evaluate(){
        return variableMap.get(name);
    }

    @Override
    public Class[] getArgumentTypes() {
        return new Class[]{Integer.TYPE};
    }


}


