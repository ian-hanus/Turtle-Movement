package frontend;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Map;

/**
 * Place where user inputs commands.
 */
public class Terminal {
    private TextArea myTextArea;
    private VBox myResults;
    private FileChooser myProgramChooser = new FileChooser();

    public Terminal() {
        myTextArea = new TextArea();
        myTextArea.getStyleClass().add("text-area-terminal");
        myResults = new VBox();
        myResults.getStyleClass().add("results-box");
        initializeProgramChooser(myProgramChooser, "*.logo");
    }

    public Node drawTerminal(FlowPane pane, View view, Canvas canvas, Configuration configuration, VariableDisplay variableDisplay, UserCommand userCommand){
        pane.getStyleClass().add("box-bot");
        myResults.getChildren().add(new Text("Returned: "));
        pane.getChildren().addAll(myTextArea, myResults);
        Button runButton = createButton("Run");
        Button helpButton = createButton("Help");
        Button loadButton = createButton("Load");
        Button loadPreferencesButton = createButton("Load Prefs");
        Button savePreferencesButton = createButton("Save Prefs");
        Button saveLibraryButton = createButton("Save Library");
        Button loadLibraryButton = createButton("Load Library");
        pane.getChildren().addAll(runButton, helpButton, loadButton, loadPreferencesButton, savePreferencesButton, loadLibraryButton, saveLibraryButton);
        runButton.setOnAction(e -> view.runCommands());
        loadButton.setOnAction(e -> loadTextFromFile());
        helpButton.setOnAction(e -> new HelpWindow());
        savePreferencesButton.setOnAction(e -> savePreferences(canvas, configuration));
        loadPreferencesButton.setOnAction(e -> loadPreferences(canvas, configuration));
        saveLibraryButton.setOnAction(e -> saveLibrary(variableDisplay, userCommand));
        loadLibraryButton.setOnAction(e -> loadLibrary(variableDisplay, userCommand));
        return pane;
    }

    private Button createButton(String label){
        Button button = new Button(label);
        button.getStyleClass().add("run-button");
        return button;
    }

    private void saveLibrary(VariableDisplay variableDisplay, UserCommand userCommand){
        Map<String, String> currentVariables = variableDisplay.getVariables();
        Map<String, String[]> currentCommands = userCommand.getUserCommands();
        Library saveLibrary = new Library(currentVariables, currentCommands);
        try {
            FileOutputStream fileOut = new FileOutputStream("./data/LibrarySave.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(saveLibrary);
            fileOut.close();
        } catch (FileNotFoundException e) {
            ErrorDisplay writeError = new ErrorDisplay("Serialization Error", "Error in file output stream");
            writeError.display();
        } catch (IOException e) {
            ErrorDisplay writeError = new ErrorDisplay("Serialization Error", "Error in saving");
            writeError.display();
        }
    }

    private void loadLibrary(VariableDisplay variableDisplay, UserCommand userCommand){
        Library loadedLibrary = null;
        try {
            FileInputStream inputStream = new FileInputStream("./data/LibrarySave.ser");
            ObjectInputStream objectInput = new ObjectInputStream(inputStream);
            loadedLibrary = (Library) objectInput.readObject();
            inputStream.close();
            objectInput.close();
            variableDisplay.setVariables(loadedLibrary.getVariableMap());
            userCommand.setUserCommands(loadedLibrary.getCommandMap());
        } catch (FileNotFoundException e) {
            ErrorDisplay inputError = new ErrorDisplay("Deserialization Error", "Input file not found");
            inputError.display();
        } catch (IOException e) {
            ErrorDisplay readError = new ErrorDisplay("Deserialization Error", "Error in deserializing");
            readError.display();
        } catch (ClassNotFoundException e) {
            ErrorDisplay invalidFile = new ErrorDisplay("Invalid File", "File does not contain appropriate information");
            invalidFile.display();
        }
        userCommand.updateUserCommands(this);
        variableDisplay.updateVariableDisplay();
    }

    private void savePreferences(Canvas canvas, Configuration configuration){
        try {
            FileOutputStream fileOut = new FileOutputStream("./data/PreferenceSave.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            Preferences pref = new Preferences(canvas.getPenColor(), canvas.getBackgroundColor(), configuration.getLanguage());
            out.writeObject(pref);
            fileOut.close();
        } catch (FileNotFoundException e) {
            ErrorDisplay writeError = new ErrorDisplay("Serialization Error", "Error in file output stream");
            writeError.display();
        } catch (IOException e) {
            ErrorDisplay writeError = new ErrorDisplay("Serialization Error", "Error in saving");
            writeError.display();
        }
    }

    private void loadPreferences(Canvas canvas, Configuration configuration){
        Preferences pref = null;
        try{
            FileInputStream inputStream = new FileInputStream("./data/PreferenceSave.ser");
            ObjectInputStream objectInput = new ObjectInputStream(inputStream);
            pref = (Preferences) objectInput.readObject();
            inputStream.close();
            objectInput.close();
            canvas.setPenColor(pref.getPenColor());
            canvas.setBackgroundColor(pref.getBackgroundColor());
            configuration.setLanguage(pref.getLanguage());
        } catch (FileNotFoundException e) {
            ErrorDisplay inputError = new ErrorDisplay("Deserialization Error", "Input file not found");
            inputError.display();
        } catch (IOException e) {
            ErrorDisplay readError = new ErrorDisplay("Deserialization Error", "Error in deserializing");
            readError.display();
        } catch (ClassNotFoundException e) {
            ErrorDisplay invalidFile = new ErrorDisplay("Invalid File", "File does not contain appropriate information");
            invalidFile.display();
        }
//        System.out.println(canvas.getBackgroundColor());
    }

    public void displayResult(double result){
        myResults.getChildren().clear();
        myResults.getChildren().add(new Text("Returned: " + result));
    }

    private void initializeProgramChooser(FileChooser fileChooser, String fileType){
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data"));
        fileChooser.setTitle("Choose a valid program");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File Chooser", fileType));
    }

    private void loadTextFromFile(){
        File programFile = myProgramChooser.showOpenDialog(new Stage());
        String input = "";
        try {
            FileInputStream fileStream = new FileInputStream(programFile);
            byte[] data = new byte[(int) programFile.length()];
            fileStream.read(data);
            fileStream.close();
            input = new String(data, "UTF-8");
        } catch (FileNotFoundException e) {
            ErrorDisplay fileError = new ErrorDisplay("File Error", "File Not Found");
            fileError.display();
        } catch(IOException e1){
            ErrorDisplay fileError = new ErrorDisplay("File Error", "Invalid File");
            fileError.display();
        } catch(NullPointerException e2){
            ErrorDisplay fileError = new ErrorDisplay("File Error", "No File Selected");
            fileError.display();
        }

        myTextArea.setText(input);
    }

    public TextArea getTextArea() {
        return myTextArea;
    }
}
