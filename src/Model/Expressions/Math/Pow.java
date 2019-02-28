package Model.Expressions.Math;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;

public class Pow extends Expression{

    private Expression base;
    private Expression exponent;

    public Pow(Expression base, Expression exponent) throws AlteringExpressionException
    {
        setArguments(base, exponent);
    }

    public void setArguments(Expression base, Expression exponent) throws AlteringExpressionException{
        finalizeStates();
        this.base = base;
        this.exponent = exponent;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        return Math.pow(base.evaluate(), exponent.evaluate());
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, expression};
    }
}
