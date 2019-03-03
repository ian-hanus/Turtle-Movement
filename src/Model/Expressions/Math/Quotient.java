package Model.Expressions.Math;
import Model.Expressions.Expression;

public class Quotient extends Expression{

    private Expression dividend;
    private Expression divisor;

    public Quotient(Expression dividend, Expression divisor) throws AlteringExpressionException
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
        return dividend.evaluate() / divisor.evaluate();
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, expression};
    }
}
