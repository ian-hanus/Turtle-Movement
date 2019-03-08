package Model.Expressions.Math;
import Model.Expressions.Interfaces.Expression;

public class Pi implements Expression{

    public Pi() {
    }

    @Override
    public double evaluate(){
        return Math.PI;
    }
}
