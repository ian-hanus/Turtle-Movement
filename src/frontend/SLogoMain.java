package frontend;

import javafx.application.Application;
import javafx.stage.Stage;

public class SLogoMain extends Application {
    public void start(Stage stage){
        View newView = new View();
        stage.setScene(newView.getScene());
        stage.show();
        newView.addCommandHistory("fd 50");
    }

    public static void main (String[] args) {
        launch(args);
    }
}
