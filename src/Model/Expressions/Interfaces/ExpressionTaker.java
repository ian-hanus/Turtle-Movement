package Model.Expressions.Interfaces;

/**
 * Marker interface indicating that the implementing class takes in a specific number of Expressions as arguments to its constructor.
 * Because of the enforced paradigm that, in general, every Expression can take in a variable number of other Expressions,
 * this helps inform any given parser of the ordinal structure of arguments while remaining agnostic to exact syntax or design.
 */

public interface ExpressionTaker extends Expression{

    /**
     * @return The exact number of Expression arguments that the implementing class is expecting as inputs
     */
    public int getDefaultNumExpressions();
}
