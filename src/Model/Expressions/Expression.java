package Model.Expressions;
import Model.Exceptions.AlteringExpressionException;
import Model.Exceptions.UninitializedExpressionException;

import java.lang.Class;

/**
 * Abstract superclass of the inheritance hierarchy that represents the possible types of commands.
 */
public abstract class Expression{

    public Expression(){ }

    private boolean finalized;
    /**
     * Evaluates the expression and determines the result of the command
     *
     * @return the result of the command being run
     */
    public abstract int evaluate() throws UninitializedExpressionException;

    /**
     * Getter for the types of arguments that the Expression expects
     *
     * @return An array of argument class types
     */
    public abstract Class[] getArgumentTypes();

    public void finalizeStates() throws AlteringExpressionException{
        if(finalized){
            throw new AlteringExpressionException();
        }
        finalized = true;
    }

    public void checkInitialization() throws UninitializedExpressionException{
        if(!finalized){
            throw new UninitializedExpressionException();
        }
    }

}