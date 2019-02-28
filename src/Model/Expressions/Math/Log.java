package Model.Expressions.Math;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;
import java.lang.Math;

public class Log extends Expression {

    private Expression input;

    public Log(Expression input) throws AlteringExpressionException
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
        return Math.log(input.evaluate());
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression};
    }
}
