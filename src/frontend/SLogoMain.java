package frontend;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SLogoMain extends Application {
    private FileChooser myImageChooser;

    public void start(Stage stage){
        stage.setResizable(false);
        CommandHistory commandHistory = new CommandHistory();
        VariableDisplay variableDisplay = new VariableDisplay();
        initializeImageChooser();
        View newView = new View(commandHistory, variableDisplay, this);
        stage.setScene(newView.getScene());
        stage.show();
    }

    private void initializeImageChooser(){
        myImageChooser = new FileChooser();
        myImageChooser.setTitle("Choose a file for turtle image");
        myImageChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data"));
        myImageChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG image file", "*.png"));
    }

    void loadTurtleImage(){
        File imageFile = myImageChooser.showOpenDialog(new Stage());
    }

    public static void main (String[] args) {
        launch(args);
    }
}
