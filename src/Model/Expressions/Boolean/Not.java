package Model.Expressions.Boolean;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;

public class Not extends Expression{

    private Expression input;

    public Not(Expression input) throws AlteringExpressionException
    {
        setArguments(input);
    }

    public void setArguments(Expression input) throws AlteringExpressionException{
        finalizeStates();
        this.input = input;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        return input.evaluate() == 0 ? 1 : 0;
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, expression};
    }
}
