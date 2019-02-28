package frontend;

import javafx.scene.control.Alert;

public class ReturnPopup extends Alert {
    public ReturnPopup(String returnValue){
        super(AlertType.INFORMATION);
        this.setTitle("Return Value");
        this.setContentText(returnValue);
    }

    public void display(){
        this.showAndWait();
    }
}
