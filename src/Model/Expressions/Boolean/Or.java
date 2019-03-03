package Model.Expressions.Boolean;
import Model.Expressions.Expression;

public class Or extends Expression{

    private Expression input1;
    private Expression input2;

    public Or(Expression input1, Expression input2) throws AlteringExpressionException
    {
        setArguments(input1, input2);
    }

    public void setArguments(Expression input1, Expression input2) throws AlteringExpressionException{
        finalizeStates();
        this.input1 = input1;
        this.input2 = input2;
    }

    @Override
    public double evaluate() throws UninitializedExpressionException {
        checkInitialization();
        return (input1.evaluate() !=0 || input2.evaluate() !=0) ? 1 : 0;
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, expression};
    }
}
