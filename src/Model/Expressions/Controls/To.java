package Model.Expressions.Controls;

import Model.Expressions.Basic.Constant;
import Model.Expressions.Basic.Procedure;
import Model.Expressions.Basic.ProcedureFactory;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;
import java.util.Map;

public class To implements Expression, VariableArgumentTaker {

    private ProcedureFactory procedureFactory;
    public To(Map<String, Procedure> procedureMap, Map<String, Constant> variableMap, String name, String[] variables, Expression... inputs){
        procedureFactory = new ProcedureFactory(variableMap, variables, inputs);
    }

    @Override
    public double evaluate() {
        return 1;
    }

    @Override
    public int getDefaultNumExpressions() {
        return 0;
    }
}
