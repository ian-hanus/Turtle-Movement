package Model.Expressions.Basic;

import Model.Expressions.Controls.Make;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;

import java.util.Map;

public class Procedure implements Expression, VariableArgumentTaker{

    Expression[] commands;
    Expression[] inputs;
    Map<String, Constant> variableMap;
    String[] variables;

    public Procedure(Map<String, Constant> variableMap, String[] variables, Expression[] commands, Expression... inputs){
        this.commands = commands;
        this.inputs = inputs;
        this.variableMap = variableMap;
        this.variables=variables;
    }

    @Override
    public double evaluate() {
        for(int x=0;x<inputs.length;x++){
            new Make(variables[x], variableMap, new Constant(inputs[x].evaluate()));
        }

        double out = 0;
        for(Expression expression:commands){
            out = expression.evaluate();
        }
        return out;
    }

    @Override
    public int getDefaultNumExpressions() {
        return variables.length;
    }
}
