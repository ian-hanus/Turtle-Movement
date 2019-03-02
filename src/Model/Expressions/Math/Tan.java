package Model.Expressions.Math;
import Model.Exceptions.UninitializedExpressionException;
import Model.Expressions.Expression;
import Model.Exceptions.AlteringExpressionException;
import java.lang.Math;

public class Tan extends Expression {

    private Expression degrees;

    public Tan(Expression degrees) throws AlteringExpressionException
    {
        setArguments(degrees);
    }

    public void setArguments(Expression degrees) throws AlteringExpressionException{
        finalizeStates();
        this.degrees = degrees;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        double radians = degrees.evaluate()/180*Math.PI;
        return Math.tan(radians);
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression};
    }
}