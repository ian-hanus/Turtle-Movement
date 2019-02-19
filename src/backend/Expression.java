package backend;

/**
 * Interface for the superclass of the inheritance hierarchy that represents the possible types of commands.
 */
public interface Expression {

    /**
     * Evaluates the expression and determines the result of the command
     * @return the result of the command being run
     */
    Object evaluate();

    /**
     * Returns the type of the expression's result. May be a Turtle object, value of a variable, or numerical result of
     * a math operation.
     * @return the type of the result of the expression being evaluated
     */
    String getReturnType();
}
