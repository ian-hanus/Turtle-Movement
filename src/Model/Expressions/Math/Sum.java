package Model.Expressions.Math;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;

public class Sum extends Expression{

    private Expression augend;
    private Expression addend;

    public Sum(Expression augend, Expression addend) throws AlteringExpressionException
    {
        setArguments(augend, addend);
    }

    public void setArguments(Expression augend, Expression addend) throws AlteringExpressionException{
        finalizeStates();
        this.augend = augend;
        this.addend = addend;
    }

    @Override
    public int evaluate() throws UninitializedExpressionException {
        checkInitialization();
        return augend.evaluate() + addend.evaluate();
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, expression};
    }
}
