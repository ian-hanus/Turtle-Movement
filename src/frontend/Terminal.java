package frontend;

import javafx.scene.control.TextArea;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Place where user inputs commands.
 */
public class Terminal {
    private String myInput;
    private List<String> myValidCommands;
    private TextArea myTextArea;

    public Terminal(){
        myValidCommands = new ArrayList<>();
        myTextArea = new TextArea();
        myTextArea.getStyleClass().add("text-area-terminal");
    }

    public void setInput(String input){
        myInput = input;
    }

    public String getInput(){
        return myInput;
    }

    public TextArea getTextArea(){
        return myTextArea;
    }
}
