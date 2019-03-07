package frontend;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;

/**
 * Place where user inputs commands.
 */
public class Terminal {
    private TextArea myTextArea;

    public Terminal() {
        myTextArea = new TextArea();
        myTextArea.getStyleClass().add("text-area-terminal");
    }

    public Node drawTerminal(FlowPane pane, View view){
        pane.getStyleClass().add("box-bot");
        pane.getChildren().addAll(new Label("Terminal"), this.getTextArea());
        Button runButton = createButton("Run");
        Button helpButton = createButton("Help");
        Button loadButton = createButton("Load");
        pane.getChildren().addAll(runButton, helpButton, loadButton);
        runButton.setOnAction(e -> view.runCommands());
        loadButton.setOnAction(e -> loadFile(view));
        helpButton.setOnAction(e -> new HelpWindow());
        return pane;
    }

    private Button createButton(String label){
        Button button = new Button(label);
        button.getStyleClass().add("run-button");
        return button;
    }

    private void loadFile(View view){
    }

    public TextArea getTextArea() {
        return myTextArea;
    }
}
