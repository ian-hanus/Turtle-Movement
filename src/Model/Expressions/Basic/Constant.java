package Model.Expressions.Basic;

import Model.Expressions.Expression;

public class Constant extends Expression{


    private double constant;

    public Constant(double constant) throws AlteringExpressionException{
        setArguments(constant);
    }

    public void setArguments(double constant) throws AlteringExpressionException {
        finalizeStates();
        this.constant = constant;
    }

    @Override
    public double evaluate(){
        return constant;
    }

    @Override
    public Class[] getArgumentTypes() {
        return new Class[]{Integer.TYPE};
    }


}


