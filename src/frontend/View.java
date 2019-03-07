package frontend;

import Model.Parser;
import Model.Result;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.Dimension;
import java.util.ArrayList;
import java.io.File;
import java.util.Deque;
import java.util.List;

public class View {
    private Scene myScene;
    private SLogoMain myMain;
    private GridPane myRightPane;
    private Canvas myCanvas;
    private VBox myTurtleStatus, myResults;
    private Terminal myTerminal;
    private Configuration myConfiguration;
    private CommandHistory myCommandHistory;
    private VariableDisplay myVariableDisplay;
    private Palette myPalette;

    private final Dimension WINDOW_SIZE = new Dimension(600, 1200);
    private final String STYLE_SHEET = "/GUIResources/ViewFormat.css";

    public View(VariableDisplay variableDisplay, SLogoMain main, Configuration configuration) {
        myMain = main;
        myConfiguration = configuration;
        myTerminal = new Terminal();
        myCommandHistory = new CommandHistory(myTerminal,this);
        myPalette = new Palette();
        myCanvas = new Canvas(new Image(new File("./src/GUIResources/turtle.png").toURI().toString()), Color.BLACK); // be cautious of path name
        myVariableDisplay = variableDisplay;

        BorderPane borderPane = new BorderPane();
        FlowPane terminalPane = new FlowPane();
        myRightPane = new GridPane();
        myRightPane.getStyleClass().add("pane-right");

        borderPane.setTop(drawTitle());
        borderPane.setRight(myRightPane);
        borderPane.setBottom(myTerminal.drawTerminal(terminalPane, this));
        borderPane.setCenter(drawCanvas());

        resetGUI();
        myScene = new Scene(borderPane, WINDOW_SIZE.getHeight(), WINDOW_SIZE.getWidth());
        myScene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
    }

    private void resetGUI(){
        myConfiguration.drawConfig(myRightPane, myMain, myCanvas);
        myCommandHistory.drawHistory(myRightPane);
        myVariableDisplay.drawVariables(myRightPane);
        drawTurtleStatus();
        myPalette.drawPalette(myRightPane);
        drawResults();
        drawCanvas();
    }

    private Node drawCanvas(){
        myCanvas.getStyleClass().add("canvas");
        return myCanvas;
    }

    public void runCommands(){
        myTerminal.setInput(myTerminal.getTextArea().getText());
        Result currentResults = null;
        myCommandHistory.addHistory(myTerminal.getTextArea().getText().split("\n"));
        myCommandHistory.updateCommandHistory(myRightPane);
        myVariableDisplay.updateVariableDisplay(myRightPane);
        try {
            Parser parser = new Parser();
            currentResults = parser.execute(myTerminal.getTextArea().getText(), myConfiguration.getLanguage().toString());
            Deque<TurtleState> currentStates = currentResults.getTurtleStates();
            for(TurtleState ts:currentStates){
                System.out.println(ts.getY());
            }
            List<TurtleState> currentListStates = new ArrayList<>();
            currentListStates.addAll(currentStates);
            myCanvas.updateCanvas(currentListStates);
            myResults.getChildren().add(new Text(Double.toString(currentResults.getReturnValue())));
        }
        catch(Exception e1){
            ErrorDisplay commandError = new ErrorDisplay("Runtime Error", e1.getMessage());
            commandError.display();
        }
        myTerminal.getTextArea().setText("");
        myResults.getChildren().removeAll();
        myPalette.drawPalette(myRightPane);
    }

    private void drawResults(){
        myResults = new RightBox("Results").getBox();
        myResults.getStyleClass().add("borderless-right");
        ScrollPane sp = new ScrollPane(myResults);
        sp.getStyleClass().add("scroll-panes");
        myRightPane.add(sp, 1, 2);
    }

    private void drawTurtleStatus(){
        myTurtleStatus = new RightBox("Turtle Status").getBox();
        myTurtleStatus.setMaxHeight(150);
        myTurtleStatus.setMinHeight(150);
        myRightPane.add(myTurtleStatus, 0, 0);
    }

    private HBox drawTitle(){
        HBox titleBox = new HBox();
        titleBox.getStyleClass().add("title");
        HBox title = new HBox();
        Text titleText = new Text("SLogo: Team 1");
        titleText.setFill(Color.WHITE);
        title.getChildren().add(titleText);
        Button newWindowButton = new Button("New Window");
        newWindowButton.setOnAction(e -> myMain.start(new Stage()));
        newWindowButton.getStyleClass().add("new-sim-button");
        titleBox.getChildren().addAll(title, newWindowButton);
        return titleBox;
    }

    public Scene getScene(){
        return myScene;
    }
}
