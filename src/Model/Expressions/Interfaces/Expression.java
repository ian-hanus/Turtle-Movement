package Model.Expressions.Interfaces;

import java.lang.Class;

/**
 * Abstract superclass of the inheritance hierarchy that represents the possible types of commands.
 */
public interface Expression{

    /**
     * Evaluates the expression and determines the result of the command
     *
     * @return the result of the command being run
     */
    public abstract double evaluate();

}