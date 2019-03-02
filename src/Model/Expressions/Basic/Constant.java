package Model.Expressions.Basic;

import Model.Expressions.Expression;

public class Constant extends Expression{


    private final double constant;

    public Constant(double constant){
        this.constant = constant;
    }

    @Override
    public double evaluate(){
        return constant;
    }

    @Override
    public Class[] getArgumentTypes() {
        return new Class[]{Double.TYPE};
    }


}


