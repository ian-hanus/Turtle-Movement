package frontend;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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
import java.util.List;

public class View {
    private Scene myScene;
    private BorderPane myBorderPane;
    private GridPane myRightPane;
    private HBox myTitlePane;
    private Canvas myCanvas;
    private VBox myHistoryBox, myVariableBox, myConfigBox, myTurtleStatus, myPalette, myResults;
    private TextArea myTerminalText;
    private ColorPicker myBackgroundColorPicker, myPenColorPicker;
    private Terminal myTerminal;
    private FlowPane myTerminalPane;
    private Configuration myConfiguration;
    private Image myTurtleImage;
    private CommandHistory myCommandHistory;
    private VariableDisplay myVariableDisplay;
    private SLogoMain myMain;
    private Slider myPenSlider;

    private final Dimension WINDOW_SIZE = new Dimension(600, 1200);
    private final String STYLE_SHEET = "/GUIResources/ViewFormat.css";

    public View(CommandHistory commandHistory, VariableDisplay variableDisplay, SLogoMain main, Configuration configuration, Stage stage) {
        myMain = main;
        myConfiguration = configuration;
        myTitlePane = new HBox();
        myCanvas = new Canvas(); // be cautious of path name
        myBorderPane = new BorderPane();
        myTerminalPane = new FlowPane();
        myTerminal = new Terminal();
        myRightPane = new GridPane();
        myBorderPane.setTop(myTitlePane);
        myRightPane.setAlignment(Pos.TOP_LEFT);
        myBorderPane.setRight(myRightPane);
        myBorderPane.setBottom(drawTerminal());
        myBorderPane.setCenter(drawCanvas());
        myCommandHistory = commandHistory;
        myVariableDisplay = variableDisplay;
        myBackgroundColorPicker = new ColorPicker();
        myPenColorPicker = new ColorPicker();
        resetGUI();
        myScene = new Scene(myBorderPane, WINDOW_SIZE.getHeight(), WINDOW_SIZE.getWidth());
        myScene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
    }

    private void resetGUI(){
        drawConfig();
        drawHistory();
        drawVariables();
        drawTurtleStatus();
        drawPalette();
        drawResults();
        drawCanvas();
        drawTitle();
    }

    private Node drawCanvas(){
        myCanvas.getStyleClass().add("canvas");
        return myCanvas;
    }

    private Node drawTerminal(){
        myTerminalPane.getStyleClass().add("box-bot");
        myTerminalPane.setMaxWidth(880);
        myTerminalPane.setTranslateX(10);
        myTerminalPane.setTranslateY(-10);
        myTerminalPane.getChildren().add(new Label("Terminal"));
        myTerminalText = new TextArea();
        myTerminalText.getStyleClass().add("text-area-terminal");
        myTerminalPane.getChildren().add(myTerminalText);
        Button runButton = new Button("Run");
        runButton.getStyleClass().add("run-button");
        myTerminalPane.getChildren().add(runButton);
        Button helpButton = new Button("Help");
        helpButton.getStyleClass().add("run-button");
        myTerminalPane.getChildren().add(helpButton);
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                myConfiguration.setPenWidth(myPenSlider.getValue());
                myTerminal.setInput(myTerminalText.getText());
                myCommandHistory.addHistory(myTerminalText.getText().split("\n"));
                updateCommandHistory();
                updateVariableDisplay();
                //TESTING
                ArrayList<TurtleState> testStates = new ArrayList<>();
                myTerminalText.setText("");
                myCanvas.updateCanvas(testStates);
            }
        });
        return myTerminalPane;
    }

    private void drawResults(){
        myResults = drawRightBox("Results");
        myRightPane.add(myResults, 1, 2);
    }

    private void drawConfig(){
        myConfigBox = drawRightBox("Configuration");
        myConfigBox.setMaxHeight(150);
        myConfigBox.setMinHeight(150);
        createLoadButton(myConfigBox);
        createBackgroundPicker(myConfigBox);
        createPenPicker(myConfigBox);
        createLanguageDropdown(myConfigBox);
        createSlider(myConfigBox);
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(myConfigBox, 1, 0);
    }

    private void drawPalette(){
        myPalette = drawRightBox("Palette");
        myPalette.getStyleClass().add("right-box");
        myRightPane.add(myPalette, 1, 1);
    }

    private void createSlider(VBox configBox){
        HBox sliderRow = new HBox();
        sliderRow.getStyleClass().add("color-picker-row");
        myPenSlider = new Slider(1, 20, 1);
        myPenSlider.getStyleClass().add("slider");
        Label penLabel = new Label("Pen Width");
        penLabel.getStyleClass().add("slider-label");
        sliderRow.getChildren().addAll(myPenSlider, penLabel);
        configBox.getChildren().add(sliderRow);
    }

    private void createLoadButton(VBox configBox){
        Button loadImageButton = new Button("");
        loadImageButton.setText("Load New Turtle Image");
        loadImageButton.getStyleClass().add("load-button");
        myConfigBox.getChildren().add(loadImageButton);
        loadImageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    myTurtleImage = myMain.loadTurtleImage();
                }
                catch(NullPointerException x){
                    ErrorDisplay invalidFile = new ErrorDisplay("Image Loader", "Invalid file");
                    invalidFile.display();
                }
                myConfiguration.setTurtleImage(myTurtleImage);
                myCanvas.setTurtleImage(myCanvas.getTurtleSprite(), myTurtleImage);
            }
        });
    }

    private void createBackgroundPicker(VBox configBox){
        HBox colorRow = createColorRow(myBackgroundColorPicker, "Background Color");
        configBox.getChildren().add(colorRow);
        myBackgroundColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                myConfiguration.setBackgroundColor(myBackgroundColorPicker.getValue());
                setCanvasBackground(myConfiguration.getBackgroundColor());
            }
        });
    }

    private void createPenPicker(VBox configBox){
        HBox colorRow = createColorRow(myPenColorPicker, "Pen Color");
        configBox.getChildren().add(colorRow);
        myPenColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                myConfiguration.setPenColor(myPenColorPicker.getValue());
                myCanvas.setPenColor(myConfiguration.getPenColor());
            }
        });
    }

    private HBox createColorRow(ColorPicker colorPicker, String labelString){
        HBox colorRow = new HBox();
        colorRow.getStyleClass().add("color-picker-row");
        colorPicker.getStyleClass().add("color-picker");
        Label label= new Label(labelString);
        label.getStyleClass().add("color-picker-label");
        colorRow.getChildren().addAll(colorPicker, label);
        return colorRow;
    }

    private void createLanguageDropdown(VBox configBox){
        ComboBox languageBox = new ComboBox();
        languageBox.setItems(FXCollections.observableArrayList(Language.values()));
        languageBox.setPromptText("Language");
        languageBox.getStyleClass().add("load-button");
        myConfigBox.getChildren().add(languageBox);
        languageBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                myConfiguration.setLanguage((Language)languageBox.getValue());
            }
        });
    }

    private void drawHistory(){
        myHistoryBox = drawRightBox("Command History");
        myHistoryBox.setMinWidth(400);
        myHistoryBox.setMaxWidth(400);
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(myHistoryBox, 0, 2);
    }

    private void drawTurtleStatus(){
        myTurtleStatus = drawRightBox("Turtle Status");
        myTurtleStatus.setMaxHeight(150);
        myTurtleStatus.setMinHeight(150);
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(myTurtleStatus, 0, 0);
    }

    private void drawVariables(){
        myVariableBox = drawRightBox("Current Variables");
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(myVariableBox, 0, 1);
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
        Button newSlogo = new Button("New Window");
        newSlogo.setOnAction(e -> myMain.start(new Stage()));
        newSlogo.getStyleClass().add("new-sim-button");
        myTitlePane.getChildren().addAll(title, newSlogo);
    }

    public void updateCommandHistory(){
        drawHistory();
        for(int k = 4; k >= 0; k--){
            if(myCommandHistory.getSize() - k > 0) {
                Button commandButton = new Button(myCommandHistory.getCommandString(myCommandHistory.getSize() - k - 1));
                myHistoryBox.getChildren().add(commandButton);
                commandButton.setOnAction(e -> myTerminalText.setText(commandButton.getText()));
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
                myVariableBox.getChildren().add(new Button(myVariableDisplay.getVariableString(myVariableDisplay.getSize() - k - 1)));
            }
        }
    }

    public Scene getScene(){
        return myScene;
    }
}
