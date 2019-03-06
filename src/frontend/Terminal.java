package frontend;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
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

    public Node drawTerminal(FlowPane pane, View view){
        pane.getStyleClass().add("box-bot");
        pane.getChildren().addAll(new Label("Terminal"), this.getTextArea());
        Button runButton = new Button("Run");
        runButton.getStyleClass().add("run-button");
        pane.getChildren().add(runButton);
        Button helpButton = new Button("Help");
        helpButton.getStyleClass().add("run-button");
        Button loadButton = new Button("Load");
        loadButton.getStyleClass().add("run-button");
        pane.getChildren().add(helpButton);
        runButton.setOnAction(e -> view.runCommands());
        return pane;
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
