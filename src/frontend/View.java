package frontend;

import Model.Exceptions.Parsing.ParsingException;
import Model.Exceptions.UninitializedExpressionException;
import Model.Parser;
import Model.Parsing;
import Model.Result;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Deque;
import java.util.List;

import static javafx.scene.paint.Color.BLACK;

public class View {
    private Scene myScene;
    private BorderPane myBorderPane;
    private SLogoMain myMain;
    private GridPane myRightPane;
    private HBox myTitlePane;
    private Canvas myCanvas;
    private VBox myHistoryBox, myVariableBox, myConfigBox, myTurtleStatus, myPaletteBox, myResults;
    private Terminal myTerminal;
    private FlowPane myTerminalPane;
    private Configuration myConfiguration;
    private Image myTurtleImage;
    private CommandHistory myCommandHistory;
    private VariableDisplay myVariableDisplay;
    private ColorPicker myBackgroundColorPicker, myPenColorPicker;
    private Slider myPenSlider;
    private Palette myPalette;
    private Parsing myParser;

    private final Dimension WINDOW_SIZE = new Dimension(600, 1200);
    private final String STYLE_SHEET = "/GUIResources/ViewFormat.css";

    public View(CommandHistory commandHistory, VariableDisplay variableDisplay, SLogoMain main, Configuration configuration, Stage stage) {
        myMain = main;
        myConfiguration = configuration;
        myTitlePane = new HBox();
        myCanvas = new Canvas(new Image(new File("./src/GUIResources/turtle.png").toURI().toString()), BLACK); // be cautious of path name
        myBorderPane = new BorderPane();
        myTerminalPane = new FlowPane();
        myTerminal = new Terminal();
        myPalette = new Palette();
        myRightPane = new GridPane();
        myBorderPane.setTop(myTitlePane);
        myBorderPane.setRight(myRightPane);
        myBorderPane.setBottom(drawTerminal());
        myBorderPane.setCenter(drawCanvas());
        myCommandHistory = commandHistory;
        myVariableDisplay = variableDisplay;
        myBackgroundColorPicker = new ColorPicker();
        myPenColorPicker = new ColorPicker();
        try {
            myParser = new Parser();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        myTerminalPane.getChildren().addAll(new Label("Terminal"), myTerminal.getTextArea());
        Button runButton = new Button("Run");
        runButton.getStyleClass().add("run-button");
        myTerminalPane.getChildren().add(runButton);
        Button helpButton = new Button("Help");
        helpButton.getStyleClass().add("run-button");
        Button loadButton = new Button("Load");
        loadButton.getStyleClass().add("run-button");
        myTerminalPane.getChildren().add(helpButton);
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                myConfiguration.setPenWidth(myPenSlider.getValue());
                myTerminal.setInput(myTerminal.getTextArea().getText());
                Result currentResults = null;
                try {
                     currentResults = myParser.execute(myTerminal.getTextArea().getText(), myConfiguration.getLanguage().toString());
                } catch (ParsingException e1) {
                    e1.printStackTrace();
                }
                myCommandHistory.addHistory(myTerminal.getTextArea().getText().split("\n"));
                updateCommandHistory();
                updateVariableDisplay();
                Deque<TurtleState> currentStates = currentResults.getTurtleStates();
                List<TurtleState> currentListStates = new ArrayList<>();
                currentListStates.addAll(currentStates);
                myTerminal.getTextArea().setText("");
                myCanvas.updateCanvas(currentListStates);
            }
        });
        return myTerminalPane;
    }

    private void drawResults(){
        myResults = drawRightBox("Results");
        myResults.getStyleClass().add("borderless-right");
        ScrollPane sp = new ScrollPane(myResults);
        sp.getStyleClass().add("scroll-panes");
        myRightPane.add(sp, 1, 2);
    }

    private void drawConfig(){
        myConfigBox = drawRightBox("Configuration");
        createLoadButton(myConfigBox);
        createBackgroundPicker(myConfigBox);
        createPenPicker(myConfigBox);
        createLanguageDropdown(myConfigBox);
        createSlider(myConfigBox);
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(myConfigBox, 1, 0);
    }

    private void drawPalette(){
        myPaletteBox = drawRightBox("Palette");
        myPaletteBox.getStyleClass().add("borderless-right");
        ScrollPane sp = new ScrollPane(myPaletteBox);
        sp.getStyleClass().add("scroll-panes");
        myRightPane.add(sp, 1, 1);
        for(Integer i:myPalette.getColorMap().keySet()){
            paletteRow(i, myPalette.getColorMap().get(i));
        }
        for(Integer i:myPalette.getShapeMap().keySet()){
            paletteRow(i, myPalette.getShapeMap().get(i));
        }
    }

    private void paletteRow(int index, Color color){
        HBox colorRow = new HBox();
        colorRow.getStyleClass().add("color-picker-row");
        Text indexTest = new Text(index + " - Color: ");
        Rectangle colorSample = new Rectangle(15, 15);
        colorSample.setFill(color);
        colorRow.getChildren().addAll(indexTest, colorSample);
        myPaletteBox.getChildren().add(colorRow);
    }

    private void paletteRow(int index, Shape shape){
        HBox shapeRow = new HBox();
        shapeRow.getStyleClass().add("color-picker-row");
        Text indexTest = new Text(index + " - Shape: ");
        shape.setFill(BLACK);
        shapeRow.getChildren().addAll(indexTest, shape);
        myPaletteBox.getChildren().add(shapeRow);
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
        loadImageButton.setOnAction(e -> loadImage());
    }

    private void loadImage(){
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

    private void createBackgroundPicker(VBox configBox){
        HBox colorRow = createColorRow(myBackgroundColorPicker, "Background Color");
        configBox.getChildren().add(colorRow);
        myBackgroundColorPicker.setOnAction(e -> myCanvas.setBackground(new Background(new BackgroundFill(myBackgroundColorPicker.getValue(), CornerRadii.EMPTY, Insets.EMPTY))));
    }

    private void createPenPicker(VBox configBox){
        HBox colorRow = createColorRow(myPenColorPicker, "Pen Color");
        configBox.getChildren().add(colorRow);
        myPenColorPicker.setOnAction(e -> myConfiguration.setPenColor(myPenColorPicker.getValue()));
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
        configBox.getChildren().add(languageBox);
        languageBox.setOnAction(e -> myConfiguration.setLanguage((Language)languageBox.getValue()));
    }

    private void drawHistory(){
        myHistoryBox = drawRightBox("Command History");
        myHistoryBox.getStyleClass().add("borderless-right");
        myRightPane.getStyleClass().add("pane-right");
        ScrollPane sp = new ScrollPane(myHistoryBox);
        sp.getStyleClass().add("scroll-panes");
        myRightPane.add(sp, 0, 2);
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
        myVariableBox.getStyleClass().add("borderless-right");
        myRightPane.getStyleClass().add("pane-right");
        ScrollPane sp = new ScrollPane(myVariableBox);
        sp.getStyleClass().add("scroll-panes");
        myRightPane.add(sp, 0, 1);
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
        Button newWindowButton = new Button("New Window");
        newWindowButton.setOnAction(e -> myMain.start(new Stage()));
        newWindowButton.getStyleClass().add("new-sim-button");
        myTitlePane.getChildren().addAll(title, newWindowButton);
    }

    public void updateCommandHistory(){
        drawHistory();
        for(String s:myCommandHistory.getCommandHistory()) {
            Button commandButton = new Button(s);
            myHistoryBox.getChildren().add(commandButton);
            commandButton.setOnAction(e -> openParams());
        }
    }

    public void updateVariableDisplay(){
        drawVariables();
        for(String s:myVariableDisplay.getVariables().keySet()){
            Button variableButton = new Button(s + " " + myVariableDisplay.getVariables().get(s));
            myVariableBox.getChildren().add(variableButton);
            variableButton.setOnAction(e -> openVariable(myVariableDisplay, myVariableDisplay.getVariables().get(s)));
        }
    }

    public void openParams(){
        ParameterWindow pw = new ParameterWindow();
    }

    public void openVariable(VariableDisplay variableDisplay, String variable){
        VariableWindow vw = new VariableWindow(variableDisplay, variable);
    }

    public Scene getScene(){
        return myScene;
    }
}
