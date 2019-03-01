package Model.Expressions.Math;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;
import java.lang.Math;

public class Random extends Expression {

    private Expression maxVal;

    public Random(Expression maxVal) throws AlteringExpressionException
    {
        setArguments(maxVal);
    }

    public void setArguments(Expression maxVal) throws AlteringExpressionException{
        finalizeStates();
        this.maxVal = maxVal;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        return Math.random()*maxVal.evaluate();
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression};
    }
}
