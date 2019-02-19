package frontend;

/**
 * Place where user inputs commands.
 */
public interface Terminal {
    /**
     * Clears the terminal, internal API called by view
     */
    static public void clearTerminal(){

    }

    /**
     * Will be used internally to get the command to frontend.CommandHistory
     * @return the String version of the desired command
     */
    static public String getInput(){
        return "";
    }
}
