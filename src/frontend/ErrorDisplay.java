package frontend;

import javafx.scene.control.Alert;

/**
 * Display an error in a command that the user entered with the desired string
 */
public class ErrorDisplay extends Alert {
    public ErrorDisplay(String type, String message){
        super(AlertType.ERROR);
        this.setTitle("Error");
        this.setHeaderText(type);
        this.setContentText(message);
    }

    public void display(){
        this.showAndWait();
    }
}
