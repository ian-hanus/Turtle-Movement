package Model.Expressions.Basic;

import Model.Expressions.Interfaces.Expression;

/**
 * A container to model constants (integer or double) as Expressions so that they may be used in Expression Trees
 */

public class Constant implements Expression{

    private double constant;

    /**
     * A container to model constants (integer or double) as Expressions so that they may be used in Expression Trees
     */
    public Constant(double constant){
        this.constant = constant;
    }

    @Override
    public double evaluate(){
        return constant;
    }


}


