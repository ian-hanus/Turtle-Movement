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
}
