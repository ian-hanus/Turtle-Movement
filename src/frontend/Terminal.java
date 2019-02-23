package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Place where user inputs commands.
 */
public class Terminal {
    private String myInput;
    private List<String> myValidCommands;

    public Terminal(){
        myValidCommands = new ArrayList<>();
    }

    public void setInput(String input){
        myInput = input;
    }

    public String getInput(){
        return myInput;
    }

    public void addCommand(String command){
        myValidCommands.add(command);
    }

    public void addAllCommands(String[] commands){
        myValidCommands.addAll(Arrays.asList(commands));
    }
}
