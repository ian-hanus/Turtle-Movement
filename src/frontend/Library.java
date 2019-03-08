package frontend;

import java.io.Serializable;
import java.util.Map;

public class Library implements Serializable {
    private Map<String, String> myVariableMap;
    private Map<String, String[]> myCommandMap;

    public Library(Map<String, String> variableMap, Map<String, String[]> commandMap){
        myCommandMap = commandMap;
        myVariableMap = variableMap;
    }

    public Map<String, String> getVariableMap(){
        return myVariableMap;
    }

    public Map<String, String[]> getCommandMap(){
        return myCommandMap;
    }
}
