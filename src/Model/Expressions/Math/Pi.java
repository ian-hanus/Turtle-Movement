package Model.Expressions.Math;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;
import java.lang.Math;

public class Pi extends Expression {


    public Pi(Expression input) throws AlteringExpressionException
    {
        setArguments();
    }

    public void setArguments() throws AlteringExpressionException{
        finalizeStates();
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        return Math.PI;
    }

    @Override
    public Class[] getArgumentTypes() {
        return new Class[]{};
    }
}