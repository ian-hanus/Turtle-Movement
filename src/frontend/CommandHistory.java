package frontend;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The command history is passed the list of valid oommands by the backend, and holds these. It then displays each
 * individual command as a button that the user can press to rerun the previous command.
 */
public class CommandHistory {
    private List<String> myCommandHistory;
    private VBox myCommandBox;
    private Terminal myTerminal;
    private View myView;
    private String myTitle;

    /**
     * The constructor holds the defining characteristics of the command history, including title, terminal that was
     * used to update command history, and the view that the command history is a part of
     * @param terminal is the terminal that the CommandHistory gets the list of commands from
     * @param view is the View that the CommandHistory is a part of
     * @param title is the displayed title of the CommandHistory
     */
    public CommandHistory(Terminal terminal, View view, String title){
        myCommandHistory = new ArrayList<>();
        myTerminal = terminal;
        myView = view;
        myTitle = title;
    }

    /**
     * Getter for the list of commands held in command history
     * @return a mutable list of commands as the command history has to be updated after each run
     */
    public List<String> getCommandHistory(){
        return myCommandHistory;
    }

//    public void addHistory(String command){
//        myCommandHistory.add(command);
//    }
//
//    public void addHistory(String[] commands){
//        myCommandHistory.addAll(Arrays.asList(commands));
//    }
//
//    public String getCommandString(int index){
//        return myCommandHistory.get(index);
//    }

    /**
     * drawHistory draws the format of the CommandHistory box, acting as a clear
     * @param pane is the pane that the CommandHistory should be added to
     */
    public void drawHistory(GridPane pane){
        myCommandBox = new RightBox(myTitle).getBox();
        myCommandBox.getStyleClass().add("borderless-right");
        ScrollPane sp = new ScrollPane(myCommandBox);
        sp.getStyleClass().add("scroll-panes");
        pane.add(sp, 0, 2);
    }

    /**
     * Updates the command history with the new objects passed in by the Results object
     * @param pane is the pane that the new CommandHistory box display should be applied to
     */
    public void updateCommandHistory(GridPane pane){
        this.drawHistory(pane);
        for(String s:this.getCommandHistory()) {
            Button commandButton = new Button(s);
            myCommandBox.getChildren().add(commandButton);
            commandButton.setOnAction(e -> runHistory(s));
        }
    }

    private void runHistory(String command){
        myTerminal.getTextArea().setText(myTerminal.getTextArea().getText() + "\n" + command);
    }

}
