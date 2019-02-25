package frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import java.awt.Dimension;
import java.util.ArrayList;
import java.io.File;


public class View {
    private Scene myScene;
    private BorderPane myBorderPane;
    private GridPane myRightPane;
    private HBox myTitlePane;
    private Canvas myCanvas;
    private VBox myHistoryBox;
    private VBox myVariableBox;
    private VBox myConfigBox;
    private CommandHistory myCommandHistory;
    private VariableDisplay myVariableDisplay;
    private SLogoMain myMain;
    private ColorPicker myColorPicker;
    private Color myBackgroundColor;
    private Terminal myTerminal;
    private FlowPane myTerminalPane;
    private Configuration myConfiguration;
    private Image myTurtleImage;

    private final Dimension WINDOW_SIZE = new Dimension(600, 900);
    private final String STYLE_SHEET = "/GUIResources/ViewFormat.css";

    public View(CommandHistory commandHistory, VariableDisplay variableDisplay, SLogoMain main, Configuration configuration) {
        myMain = main;
        myConfiguration = configuration;
        myTitlePane = new HBox();
        myCanvas = new Canvas(new Image(new File("./src/GUIResources/turtle.png").toURI().toString()), Color.BLACK); // be cautious of path name
        myBorderPane = new BorderPane();
        myTerminalPane = new FlowPane();
        myTerminal = new Terminal();
        myScene = new Scene(myBorderPane, WINDOW_SIZE.getHeight(), WINDOW_SIZE.getWidth());
        myScene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
        myRightPane = new GridPane();
        myBorderPane.setTop(myTitlePane);
        myRightPane.setAlignment(Pos.TOP_LEFT);
        myBorderPane.setRight(myRightPane);
        myBorderPane.setBottom(drawTerminal());
        myBorderPane.setCenter(drawCanvas());
        myCommandHistory = commandHistory;
        myVariableDisplay = variableDisplay;
        myColorPicker = new ColorPicker();
        resetGUI();
    }

    private void resetGUI(){
        drawConfig();
        drawHistory();
        drawVariables();
        drawCanvas();
        drawTitle();
    }

    private Node drawCanvas(){
        myCanvas.getStyleClass().add("canvas");
        myCanvas.setTranslateY(-2);
        myCanvas.setTranslateX(8);
        return myCanvas;
    }

    private Node drawTerminal(){
        myTerminalPane.getStyleClass().add("box-bot");
        myTerminalPane.setMaxWidth(880);
        myTerminalPane.setTranslateX(10);
        myTerminalPane.setTranslateY(-10);
        myTerminalPane.getChildren().add(new Label("Terminal"));
        TextArea terminalText = new TextArea();
        terminalText.getStyleClass().add("text-area-terminal");
        myTerminalPane.getChildren().add(terminalText);
        Button runButton = new Button();
        runButton.setText("Run");
        runButton.getStyleClass().add("run-button");
        myTerminalPane.getChildren().add(runButton);
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                myTerminal.setInput(terminalText.getText());
                myCommandHistory.addHistory(terminalText.getText().split("\n"));
                updateCommandHistory();
                updateVariableDisplay();
                terminalText.setText("");
                //TESTING
                ArrayList<TurtleState> testStates = new ArrayList<>();
                /*testStates.add(new TurtleState(100,100, 0, true, true ));
                testStates.add(new TurtleState(120, 160, 0, true, true));
                testStates.add(new TurtleState(-120, -20, 80, true, true));
                */
                myCanvas.updateCanvas(testStates);
            }
        });
        return myTerminalPane;
    }

    private void drawConfig(){
        myConfigBox = drawRightBox("Configuration");
        Button loadImageButton = new Button("");
        loadImageButton.setText("Load New Turtle Image");
        loadImageButton.getStyleClass().add("load-button");
        myConfigBox.getChildren().add(loadImageButton);
        myColorPicker.getStyleClass().add("color-picker");
        myConfigBox.getChildren().add(myColorPicker);
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(myConfigBox, 0, 0);
        loadImageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                myTurtleImage = myMain.loadTurtleImage();
                myConfiguration.setTurtleImage(myTurtleImage);
            }
        });
        myColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                myConfiguration.setBackgroundColor(myColorPicker.getValue());
                myBackgroundColor = myConfiguration.getBackgroundColor();
                setCanvasBackground(myBackgroundColor);
            }
        });
    }

    private void drawHistory(){
        myHistoryBox = drawRightBox("Command History");
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(myHistoryBox, 0, 1);
    }

    private void drawVariables(){
        myVariableBox = drawRightBox("Current Variables");
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(myVariableBox, 0, 2);
    }

    private VBox drawRightBox(String title){
        VBox rightBox = new VBox();
        rightBox.getStyleClass().add("box-right");
        Label label = new Label(title);
        rightBox.getChildren().add(label);
        return rightBox;
    }

    private void drawTitle(){
        myTitlePane.getStyleClass().add("title");
        HBox title = new HBox();
        Text titleText = new Text("SLogo: Team 1");
        titleText.setFill(Color.WHITE);
        title.getChildren().add(titleText);
        myTitlePane.getChildren().add(title);
    }

    public void updateCommandHistory(){
        drawHistory();
        for(int k = 4; k >= 0; k--){
            if(myCommandHistory.getSize() - k > 0) {
                myHistoryBox.getChildren().add(new Text(myCommandHistory.getCommandString(myCommandHistory.getSize() - k - 1)));
            }
        }
    }

    private void setCanvasBackground(Color color){
        myCanvas.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void updateVariableDisplay(){
        drawVariables();
        for(int k = 4; k >= 0; k--){
            if(myVariableDisplay.getSize() - k > 0){
                myVariableBox.getChildren().add(new Text(myVariableDisplay.getVariableString(myVariableDisplay.getSize() - k - 1)));
            }
        }
    }

    public Scene getScene(){
        return myScene;
    }
}
