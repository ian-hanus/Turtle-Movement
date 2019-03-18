package frontend;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class where new views and stages are created and controlled
 */
public class SLogoMain extends Application {
    private List<Stage> myStages =new ArrayList<>();
    private List<View> myViews = new ArrayList<>();

    private FileChooser myImageChooser;

    /**
     * Creates a new SLogo view on the entered stage. Can be called on a new stage, or an already activated stage to
     * act as a reset
     * @param stage is the stage that the new SLogo scene will be created on
     */
    public void start(Stage stage){
        stage.setResizable(false);
        initializeImageChooser();
        View newView = new View(this);
        myStages.add(stage);
        myViews.add(newView);
        stage.setScene(newView.getScene());
        stage.show();
    }


    private void initializeImageChooser(){
        myImageChooser = new FileChooser();
        myImageChooser.setTitle("Choose a file for turtle image");
        myImageChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data"));
        myImageChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG image file", "*.png", "*.jpg"));
    }

    /**
     * Load the new image to be set as the turtle from the chooser as a .png or .jpg file
     * @return the image taken in from the file
     */
    public Image loadTurtleImage(){
        File imageFile = myImageChooser.showOpenDialog(new Stage());
        return new Image(imageFile.toURI().toString());
    }

    /**
     * Main program that actually begins the running of SLogo
     * @param args
     */
    public static void main (String[] args) {
        launch(args);
    }
}
