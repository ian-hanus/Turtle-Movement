package Model.Expressions.Math;
import Model.Expressions.Interfaces.Expression;

public class Product extends Expression{

    private Expression multiplicand;
    private Expression multiplier;

    public Product(Expression multiplicand, Expression multiplier) throws AlteringExpressionException
    {
        setArguments(multiplicand, multiplier);
    }

    public void setArguments(Expression multiplicand, Expression multiplier) throws AlteringExpressionException{
        finalizeStates();
        this.multiplicand = multiplicand;
        this.multiplier = multiplier;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        return multiplicand.evaluate() * multiplier.evaluate();
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, expression};
    }
}
