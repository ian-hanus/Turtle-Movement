package Model.Expressions.Math;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.ExpressionTaker;

public class Atan implements Expression, ExpressionTaker{

    private Expression[] inputs;

    public Atan(Expression... inputs)
    {
        if(inputs.length!= getDefaultNumExpressions()){
            throw new IllegalArgumentException(String.format("Exactly %d Expressions required", getDefaultNumExpressions()));
        }
        this.inputs=inputs;
    }


    @Override
    public double evaluate(){
        double radians = inputs[0].evaluate()/180*Math.PI;
        return Math.atan(radians);
    }

    @Override
    public int getDefaultNumExpressions() {
        return 1;
    }
}
