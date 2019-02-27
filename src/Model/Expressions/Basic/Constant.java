package Model.Expressions.Basic;

import Model.Expressions.Expression;

public class Constant extends Expression{


    private final int constant;

    public Constant(int constant){
        this.constant = constant;
    }

    @Override
    public int evaluate(){
        return constant;
    }

    @Override
    public Class[] getArgumentTypes() {
        return new Class[]{Integer.TYPE};
    }


}


