import java.util.ArrayList;
import java.util.List;

/**
 * Command history interface will allow the backend to access and interface with the past commands entered.
 * The backend can also get the new command through getHistory(0)
 */
public interface CommandHistory {
    /**
     * Will return a command that has been previously entered. Will
     * return an exception if the user tries to get a command past
     * when the last one was entered
     * @param index is an integer representing how many commands ago the desired command was entered
     * @return the String of the desired command
     */
    static public Command getHistory(int index){
        List<Command> history = new ArrayList<Command>();
        return history.get(index);
    }

    /**
     * Add the new command to the history
     * @param newCommand is the string form of the command that was entered in the terminal
     */
    static public void addHistory(String newCommand){
        List<String> history = new ArrayList<String>();
        history.add(newCommand);
    }
}
