package frontend;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Place where user inputs commands.
 */
public class Terminal {
    private TextArea myTextArea;
    private VBox myResults;

    public Terminal() {
        myTextArea = new TextArea();
        myTextArea.getStyleClass().add("text-area-terminal");
        myResults = new VBox();
        myResults.getStyleClass().add("results-box");
    }

    public Node drawTerminal(FlowPane pane, View view){
        pane.getStyleClass().add("box-bot");
        myResults.getChildren().add(new Text("Returned: "));
        pane.getChildren().addAll(myTextArea, myResults);
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

    public void displayResult(double result){
        myResults.getChildren().clear();
        myResults.getChildren().add(new Text("Returned: " + result));
    }

    private void loadFile(View view){
    }

    public TextArea getTextArea() {
        return myTextArea;
    }

    public VBox getResults(){
        return myResults;
    }
}
