package frontend;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class UserCommand {
    private Map<String, String> myDefinedCommands;
    private VBox myUserCommandBox;

    public UserCommand(){
        myDefinedCommands = new HashMap<String, String>();
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
}
