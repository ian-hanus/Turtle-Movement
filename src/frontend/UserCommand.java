package frontend;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

/**
 * Object containing and displaying the user defined commands that makes it possible to call these
 * user defined commands again on the backend
 */
public class UserCommand {
    private Map<String, String[]> myDefinedCommands;
    private VBox myUserCommandBox;

    /**
     * Constructor creating the map that will user defined commands
     */
    public UserCommand(){
        myDefinedCommands = new HashMap<String, String[]>();
    }

    /**
     * Draws the actual box that will display the user defined commands as buttons, that when pressed, will open
     * parameter windows to be run
     * @param gridPane is the Pane that the box representing the user commands will be added to
     */
    public void drawUserCommands(GridPane gridPane){
        myUserCommandBox = new RightBox("User Command").getBox();
        ScrollPane sp = new ScrollPane(myUserCommandBox);
        myUserCommandBox.getStyleClass().add("borderless-right");
        sp.getStyleClass().add("scroll-panes");
        gridPane.add(sp, 1, 2);
    }

//    public VBox getUserCommandBox(){
//        return myUserCommandBox;
//    }

    /**
     * Updates the user commands given by the backend
     * @param terminal is the terminal that can run the commands, so the proper text can entered to be run on pressing
     *                 the user defined command button and filling out the parameters
     */
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

    /**
     * Getter for the map representing the user commands, used to save the user commands in a library to be loaded later
     * @return the map representing the user defined commands
     */
    public Map<String, String[]> getUserCommands(){
        return myDefinedCommands;
    }

    /**
     * Sets the list of user commands, in the case that user commands are loaded from a library
     * @param inputCommands is the map representing the user commands to be set in the display
     */
    public void setUserCommands(Map<String, String[]> inputCommands){
        myDefinedCommands = inputCommands;
    }
}
