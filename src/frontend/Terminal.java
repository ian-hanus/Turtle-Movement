package frontend;

import Model.Parser;
import Model.Parsing;
import Model.Result;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Place where user inputs commands.
 */
public class Terminal {
    private String myInput;
    private List<String> myValidCommands;
    private TextArea myTextArea;

    public Terminal() {
        myValidCommands = new ArrayList<>();
        myTextArea = new TextArea();
        myTextArea.getStyleClass().add("text-area-terminal");
    }

    public void setInput(String input) {
        myInput = input;
    }

    public String getInput() {
        return myInput;
    }

    public TextArea getTextArea() {
        return myTextArea;
    }
}
