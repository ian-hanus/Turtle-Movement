package Model.Expressions.Math;
import Model.Expressions.Interfaces.Expression;

public class Minus extends Expression{

    private Expression minuend;

    public Minus(Expression minuend) throws AlteringExpressionException
    {
        setArguments(minuend);
    }

    public void setArguments(Expression minuend) throws AlteringExpressionException{
        finalizeStates();
        this.minuend = minuend;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        return -minuend.evaluate();
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression};
    }
}
