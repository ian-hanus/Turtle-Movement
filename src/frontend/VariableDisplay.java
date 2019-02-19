package frontend;

public interface VariableDisplay {
    /**
     * Add a new variable
     * @param v variable added
     */
    default public void addVariable(Variable v){
    }

    /**
     * Set variables value to a String
     * @param varName is the name of the variable that will be set
     */
    default public void setValue(String varName){
    }

    /**
     * Remove a variable from the variable display
     * @param varName is the variable that you want removed
     */
    void removeVariable(String varName);

    /**
     * Get the value held in a variable
     * @param varName is the variable to get the matching value
     */
    void getValue(String varName);

}
