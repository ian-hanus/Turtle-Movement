package Model.Expressions.Basic;

import Model.Expressions.Interfaces.Expression;

import java.util.Map;

public class Variable implements Expression{

    private String name;
    private Map<String, Constant> variableMap;

    public Variable(String name, Map<String, Constant> map) {
        this.name = name;
        this.variableMap = map;
    }

    @Override
    public double evaluate(){
        if(!variableMap.keySet().contains(name)){
            variableMap.put(name, new Constant(0));
        }
        return variableMap.get(name).evaluate();

    }

}


