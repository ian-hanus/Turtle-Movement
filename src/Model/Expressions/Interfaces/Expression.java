package Model.Expressions.Interfaces;

import java.lang.Class;

/**
 * Abstract superclass of the inheritance hierarchy that represents the possible types of commands.
 */
public interface Expression{

    /**
     * Evaluates the expression and determines the result of the command. A strictly enforced design invariant is that
     * the evaluate() method must be able to be called repeatedly (such as in a loop) and should return correctly with
     * regard to global changes such as altering variable and procedural definitions or recursion. This result is naturally
     * accomplished through adherence to the nested Expression Tree design pattern as detailed in the master design documents.
     *
     * @return the result of the command being run on the inputs given in its constructor
     */
    public abstract double evaluate();

}