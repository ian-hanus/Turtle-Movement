package Model.Expressions.Basic;

import Model.Expressions.Interfaces.Expression;

import java.util.Map;

public class Variable implements Expression{

    private String name;
    private Map<String, Double> variableMap;

    public Variable(String name, Map<String, Double> map) {
        this.name = name;
        this.variableMap = map;
    }

    @Override
    public double evaluate(){
        return variableMap.get(name);
    }

}


