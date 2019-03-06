package Model.Expressions.Boolean;
import Model.Expressions.Interfaces.Expression;
import Model.Expressions.Interfaces.VariableArgumentTaker;

public class Equal implements Expression, VariableArgumentTaker {

    private Expression[] inputs;

    public Equal(Expression... inputs) {
        if(inputs.length == 0){
            throw new IllegalArgumentException(String.format("Insufficient Expressions input, at least %d expected", getDefaultNumExpressions()));
        }
        this.inputs = inputs;
    }

    @Override
    public double evaluate() {
        double initial = inputs[0].evaluate();
        if(inputs.length==1){
            return 1;
        }
        for(int x=1;x<inputs.length;x++){
            if(inputs[x].evaluate()!=initial){
                return 0;
            }
        }
        return 1;
    }
    public int getDefaultNumExpressions(){
        return 2;
    }
}
