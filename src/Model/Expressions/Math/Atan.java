package Model.Expressions.Math;
import Model.Expressions.Expression;

import java.lang.Math;

public class Atan extends Expression {

    private Expression degrees;

    public Atan(Expression degrees) throws AlteringExpressionException
    {
        setArguments(degrees);
    }

    public void setArguments(Expression degrees) throws AlteringExpressionException{
        finalizeStates();
        this.degrees = degrees;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        double radians = degrees.evaluate()/180*Math.PI;
        return Math.atan(radians);
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression};
    }
}
