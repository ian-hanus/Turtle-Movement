package frontend;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Command history interface will allow the backend to access and interface with the past commands entered.
 * The backend can also get the new command through getHistory(0)
 */
public class CommandHistory {
    private List<String> myCommandHistory;
    private VBox myCommandBox;

    public CommandHistory(){
        myCommandHistory = new ArrayList<>();
    }

    public List<String> getCommandHistory(){
        return myCommandHistory;
    }

    public void addHistory(String command){
        myCommandHistory.add(command);
    }

    public void addHistory(String[] commands){
        myCommandHistory.addAll(Arrays.asList(commands));
    }

    public int getSize(){
        return myCommandHistory.size();
    }

    public String getCommandString(int index){
        return myCommandHistory.get(index);
    }

    public void drawHistory(GridPane pane){
        myCommandBox = new RightBox("Command History").getBox();
        myCommandBox.getStyleClass().add("borderless-right");
        ScrollPane sp = new ScrollPane(myCommandBox);
        sp.getStyleClass().add("scroll-panes");
        pane.add(sp, 0, 2);
    }

    public VBox getCommandBox(){
        return myCommandBox;
    }

    public void updateCommandHistory(GridPane pane){
        this.drawHistory(pane);
        for(String s:this.getCommandHistory()) {
            Button commandButton = new Button(s);
            this.getCommandBox().getChildren().add(commandButton);
            commandButton.setOnAction(e -> openParams());
        }
    }

    public void openParams(){
        ParameterWindow pw = new ParameterWindow();
    }
}
