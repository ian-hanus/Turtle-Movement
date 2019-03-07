package frontend;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SLogoMain extends Application {
    private List<Stage> myStages =new ArrayList<>();
    private List<View> myViews = new ArrayList<>();

    private FileChooser myImageChooser;

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

    Image loadTurtleImage(){
        File imageFile = myImageChooser.showOpenDialog(new Stage());
        return new Image(imageFile.toURI().toString());
    }


    public static void main (String[] args) {
        launch(args);
    }
}
