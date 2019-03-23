package frontend;

import Model.Exceptions.Parsing.ParsingException;
import Model.Parser;
import Model.Result;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Deque;

public class View {
    private Scene myScene;
    private SLogoMain myMain;
    private GridPane myRightPane;
    private Canvas myCanvas;
    private Terminal myTerminal;
    private Configuration myConfiguration;
    private CommandHistory myCommandHistory;
    private UserCommand myUserCommand;
    private VariableDisplay myVariableDisplay;
    private Palette myPalette;
    private TurtleStatusDisplay myTurtleStatusDisplay;
    private Parser myParser;

    private final int GUI_WIDTH = 600;
    private final int GUI_HEIGHT = 1200;
    private final Dimension WINDOW_SIZE = new Dimension(GUI_WIDTH, GUI_HEIGHT);
    private final String STYLE_SHEET = "/GUIResources/ViewFormat.css";

    /**
     * Class that combines and controls the display of each of the individual components used in the SLogo program
     * @param main is the SLogoMain that the view is being run on
     */
    public View(SLogoMain main) {
        BorderPane borderPane = new BorderPane();
        FlowPane terminalPane = new FlowPane();
        myRightPane = new GridPane();
        myRightPane.getStyleClass().add("pane-right");

        myMain = main;
        myConfiguration = new Configuration();
        myTerminal = new Terminal();
        myCommandHistory = new CommandHistory(myTerminal,this, "Command History");
        myPalette = new Palette();
        myVariableDisplay = new VariableDisplay(myRightPane);
        myCanvas = new Canvas(this, myTerminal);
        myUserCommand = new UserCommand();
        myParser = new Parser();


        borderPane.setTop(drawTitle());
        borderPane.setRight(myRightPane);
        borderPane.setBottom(myTerminal.drawTerminal(terminalPane, this, myCanvas, myConfiguration, myVariableDisplay, myUserCommand));
        borderPane.setCenter(drawCanvas());

        resetGUI();
        myScene = new Scene(borderPane, WINDOW_SIZE.getHeight(), WINDOW_SIZE.getWidth());
        myScene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
    }

    private void resetGUI(){
        myConfiguration.drawConfig(myRightPane, myMain, myCanvas);
        myCommandHistory.drawHistory(myRightPane);
        myVariableDisplay.drawVariables();
        myUserCommand.drawUserCommands(myRightPane);
        drawTurtleStatus();
        myPalette.drawPalette(myRightPane);
        //drawResults();
        drawCanvas();
    }

    private Node drawCanvas(){
        myCanvas.getStyleClass().add("canvas");
        return myCanvas;
    }

    /**
     * Runs the actual commands and updates each of the necessary displays, including canvas, command history, variables,
     * user defined commands, and configuration
     */
    public void runCommands(){
        try {
            Result currentResults = myParser.execute(myTerminal.getTextArea().getText(), myConfiguration.getLanguage().toString());
            Deque<TurtleState> currentStates = currentResults.getTurtleStates();
            myCanvas.updateCanvas(currentStates);
            myTerminal.displayResult((currentResults.getReturnValue()));
            myTurtleStatusDisplay.refresh(myCanvas.getTurtleList());
//            myCommandHistory.addHistory(currentResults.getCommandHistory());
//            myCommandHistory.updateCommandHistory(myRightPane);
            myUserCommand.setUserCommands(currentResults.getProcedureMap());
            myUserCommand.updateUserCommands(myTerminal);
            myVariableDisplay.setVariables(currentResults.getVariableMap());
            myVariableDisplay.updateVariableDisplay();
        }
        catch(ParsingException e1){
            ErrorDisplay commandError = new ErrorDisplay("Runtime Error", e1.getMessage());
            commandError.display();
        }
        catch(NullPointerException e2){
            ErrorDisplay noCommandError = new ErrorDisplay("User Input", "Command not valid");
            noCommandError.display();
        }
        myTerminal.getTextArea().setText("");
        myPalette.drawPalette(myRightPane);
    }

    private void drawTurtleStatus(){
        myTurtleStatusDisplay = new TurtleStatusDisplay(myTerminal, this, myCanvas.getTurtleList());
        myTurtleStatusDisplay.getStyleClass().add("box-right");
        myRightPane.add(myTurtleStatusDisplay, 0, 0);
    }

    private HBox drawTitle(){
        HBox titleBox = new HBox();
        titleBox.getStyleClass().add("title");
        HBox title = new HBox();
        Text titleText = new Text("SLogo: Team 1");
        titleText.setFill(Color.WHITE);
        title.getChildren().add(titleText);
        Button newWindowButton = new Button("New Window"){
            @Override
            public void requestFocus(){};

        };
        newWindowButton.setOnAction(e -> myMain.start(new Stage()));
        newWindowButton.getStyleClass().add("new-sim-button");
        titleBox.getChildren().addAll(title, newWindowButton);
        return titleBox;
    }

    /**
     * Returns the scene corresponding to the view, to be diplayed on the Stage in main
     * @return Scene used to display state of SLogo on the stage
     */
    public Scene getScene(){
        return myScene;
    }
}
