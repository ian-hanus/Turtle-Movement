package frontend;

/**
 * Place where user inputs commands.
 */
public interface Terminal {


    /**
     * Will be used internally to get the command to frontend.CommandHistory
     * @return the String version of the desired command
     */
    String getInput();
}
