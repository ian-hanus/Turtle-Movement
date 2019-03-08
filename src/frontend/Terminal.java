package frontend;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Place where user inputs commands.
 */
public class Terminal {
    private TextArea myTextArea;
    private VBox myResults;
    private FileChooser myProgramChooser;

    public Terminal() {
        myTextArea = new TextArea();
        myTextArea.getStyleClass().add("text-area-terminal");
        myResults = new VBox();
        myResults.getStyleClass().add("results-box");
        initializeProgramChooser();
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

    private void initializeProgramChooser(){
        myProgramChooser = new FileChooser();
        myProgramChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data"));
        myProgramChooser.setTitle("Choose a valid program (*.logo)");
        myProgramChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".logo file", "*.logo"));
    }

    private void loadTextFromFile(){
        File programFile = myProgramChooser.showOpenDialog(new Stage());

        try {
            FileInputStream fileStream = new FileInputStream(programFile);
            byte[] data = new byte[(int) programFile.length()];
        } catch (FileNotFoundException e) {
            ErrorDisplay fileError = new ErrorDisplay("File Error", "File Not Found");
            fileError.display();
        }
    }

    public TextArea getTextArea() {
        return myTextArea;
    }

    public VBox getResults(){
        return myResults;
    }
}
