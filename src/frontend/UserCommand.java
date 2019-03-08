package frontend;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class UserCommand {
    private Map<String, String[]> myDefinedCommands;
    private VBox myUserCommandBox;

    public UserCommand(){
        myDefinedCommands = new HashMap<String, String[]>();
    }

    public void drawUserCommands(GridPane gridPane){
        myUserCommandBox = new RightBox("User Command").getBox();
        ScrollPane sp = new ScrollPane(myUserCommandBox);
        myUserCommandBox.getStyleClass().add("borderless-right");
        sp.getStyleClass().add("scroll-panes");
        gridPane.add(sp, 1, 2);
    }

    public VBox getUserCommandBox(){
        return myUserCommandBox;
    }

    public void updateUserCommands(Terminal terminal){
        for(String command:myDefinedCommands.keySet()){
            Button button = new Button(command);
            myUserCommandBox.getChildren().add(button);
            button.setOnAction(e -> runCustomParams(command, myDefinedCommands.get(command), terminal));
        }
    }

    private void runCustomParams(String command, String[] parameters, Terminal terminal){
        ParameterWindow parameterWindow = new ParameterWindow(command, parameters);
        Double[] parameterValues = parameterWindow.getNewParams();
        String callCommand = command;
        for(double d:parameterValues){
            callCommand = callCommand + " " + d;
        }
        terminal.getTextArea().setText(callCommand);
    }
}
