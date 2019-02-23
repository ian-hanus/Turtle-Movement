package frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariableDisplay {
    private Map<String, String> myVariableHistory;
    private List<String> myKeyOrder;

    public VariableDisplay(){
        myVariableHistory = new HashMap<>();
        myKeyOrder = new ArrayList<>();
    }

    public void addVariable(String name, String value){
        myVariableHistory.put(name, value);
        myKeyOrder.add(name);
    }

    public String getVariable(int index){
        return myVariableHistory.get(myKeyOrder.get(index));
    }

//    /**
//     * Add a new variable
//     * @param v variable added
//     */
//    default public void addVariable(Variable v){
//    }
//
//    /**
//     * Set variables value to a String
//     * @param varName is the name of the variable that will be set
//     */
//    default public void setValue(String varName){
//    }
//
//    /**
//     * Remove a variable from the variable display
//     * @param varName is the variable that you want removed
//     */
//    void removeVariable(String varName);
//
//    /**
//     * Get the value held in a variable
//     * @param varName is the variable to get the matching value
//     */
//    void getValue(String varName);

}
