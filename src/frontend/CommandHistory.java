package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Command history interface will allow the backend to access and interface with the past commands entered.
 * The backend can also get the new command through getHistory(0)
 */
public class CommandHistory {
    private List<String> myCommandHistory;

    public CommandHistory(){
        myCommandHistory = new ArrayList<>();
    }

    public List<String> getCommandHistory(){
        return myCommandHistory;
    }

    public void addHistory(String command){
        myCommandHistory.add(command);
    }

    public void addHistory(String[] commands){
        myCommandHistory.addAll(Arrays.asList(commands));
    }

    public int getSize(){
        return myCommandHistory.size();
    }

    public String getCommandString(int index){
        return myCommandHistory.get(index);
    }
//    /**
//     * Will return a command that has been previously entered. Will
//     * return an exception if the user tries to get a command past
//     * when the last one was entered
//     * @param index is an integer representing how many commands ago the desired command was entered
//     * @return the String of the desired command
//     */
//    String getHistory(int index);
//
//    /**
//     * Add the new command to the history
//     * @param newCommand is the string form of the command that was entered in the terminal
//     */
//    void addHistory(String newCommand);
}
