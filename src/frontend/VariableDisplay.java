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

    public int getSize(){
        return myKeyOrder.size();
    }

    public String getVariableString(int index){
        String key = myKeyOrder.get(index);
        return key + " = " + myVariableHistory.get(key);
    }
}
