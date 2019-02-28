package Model.Expressions.Math;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;

public class Remainder extends Expression{

    private Expression dividend;
    private Expression divisor;

    public Remainder(Expression dividend, Expression divisor) throws AlteringExpressionException
    {
        setArguments(dividend, divisor);
    }

    public void setArguments(Expression dividend, Expression divisor) throws AlteringExpressionException{
        finalizeStates();
        this.dividend = dividend;
        this.divisor = divisor;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        return dividend.evaluate() % divisor.evaluate();
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, expression};
    }
}
