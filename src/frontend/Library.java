package frontend;

import java.io.Serializable;
import java.util.Map;

/**
 * Library object that contains variables and commands to be loaded into the new program from a .ser file
 */
public class Library implements Serializable {
    private Map<String, String> myVariableMap;
    private Map<String, String[]> myCommandMap;

    /**
     * Constructor for the library object
     * @param variableMap is the map of <String, String> that contains the list of variables and their values
     * @param commandMap is the map of <String, String[]> that contains the name of the command and the strings of the
     *                   list of SLogo commands that correspond to it
     */
    public Library(Map<String, String> variableMap, Map<String, String[]> commandMap){
        myCommandMap = commandMap;
        myVariableMap = variableMap;
    }

    /**
     * Gets the map of variables and their values
     * @return a map with a String variable name as the key and the String variable value as the value
     */
    public Map<String, String> getVariableMap(){
        return myVariableMap;
    }

    /**
     * Gets the map of commands and the SLogo commands corresponding to the command name
     * @return a map with a user defined String command name as the key and the list of SLogo commands corresponding to
     * the user define command as the values
     */
    public Map<String, String[]> getCommandMap(){
        return myCommandMap;
    }
}
