package Model.Expressions.Math;
import Model.Expressions.Interfaces.Expression;

import java.lang.Math;

public class Sin extends Expression {

    private Expression degrees;

    public Sin(Expression degrees) throws AlteringExpressionException
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
        return Math.sin(radians);
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression};
    }
}
