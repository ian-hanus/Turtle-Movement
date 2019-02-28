package Model.Expressions.Math;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;

public class Difference extends Expression{

    private Expression minuend;
    private Expression subtrahend;

    public Difference(Expression augend, Expression addend) throws AlteringExpressionException
    {
        setArguments(augend, addend);
    }

    public void setArguments(Expression minuend, Expression subtrahend) throws AlteringExpressionException{
        finalizeStates();
        this.minuend = minuend;
        this.subtrahend = subtrahend;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        return minuend.evaluate() - subtrahend.evaluate();
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, expression};
    }
}
