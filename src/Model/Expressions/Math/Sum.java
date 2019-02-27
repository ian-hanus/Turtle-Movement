package Model.Expressions.Math;
import Model.Expressions.Expression;

public class Sum extends Expression {

    private Expression augend;
    private Expression addend;

    public Sum(Expression augend, Expression addend){
        this.augend = augend;
        this.addend = addend;
    }

    @Override
    public int evaluate(){
        return augend.evaluate()+addend.evaluate();
    }

    @Override
    public Class[] getArgumentTypes() {
        Class expression = super.getClass();
        return new Class[]{expression, expression};
    }
}
