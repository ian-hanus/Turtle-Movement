package Model.Expressions.Basic;

import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;

import java.util.Map;

public class ProcedureFactory implements VariableArgumentTaker {

    Expression[] commands;
    Map<String, Constant> variableMap;
    String[] variables;

    public ProcedureFactory(Map<String, Constant> variableMap, String[] variables, Expression... inputs){
        this.commands = inputs;
        this.variableMap = variableMap;
        this.variables=variables;
    }

    public Procedure acceptParameters(Expression... inputs){
        if(inputs.length>variables.length){
            throw new IllegalArgumentException("More inputs given than there are variables");
        }

        return new Procedure(variableMap, variables, commands, inputs);
    }

    @Override
    public int getDefaultNumExpressions(){
        return variables.length;
    }

    public String[] getVariables() {
        return variables;
    }

}
