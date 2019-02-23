package frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class View {
    private Scene myScene;
    private BorderPane myBorderPane;
    private GridPane myRightPane;
    private HBox myTitlePane;
    private VBox myHistoryBox;
    private List<String> myCommandHistory;

    private final Dimension WINDOW_SIZE = new Dimension(600, 900);
    private final String STYLE_SHEET = "/GUIResources/ViewFormat.css";

    public View() {
        myTitlePane = new HBox();
        myBorderPane = new BorderPane();
        myScene = new Scene(myBorderPane, WINDOW_SIZE.getHeight(), WINDOW_SIZE.getWidth());
        myScene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
        myRightPane = new GridPane();
        myBorderPane.setTop(myTitlePane);
        myRightPane.setAlignment(Pos.TOP_LEFT);
        myBorderPane.setRight(myRightPane);
        myBorderPane.setBottom(drawTerminal());
        myBorderPane.setCenter(drawCanvas());
        myCommandHistory = new ArrayList<>();
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
        HBox canvas = new HBox();
        canvas.getStyleClass().add("canvas");
        canvas.getChildren().add(new Label("Canvas"));
        canvas.setTranslateY(-2);
        canvas.setTranslateX(8);
        return canvas;
    }

    private Node drawTerminal(){
        FlowPane terminal = new FlowPane();
        terminal.getStyleClass().add("box-bot");
        terminal.setMaxWidth(880);
        terminal.setTranslateX(10);
        terminal.setTranslateY(-10);
        TextArea terminalText = new TextArea();
        terminalText.getStyleClass().add("text-area-terminal");
        terminal.getChildren().add(terminalText);
        Button runButton = new Button();
        runButton.setText("Run");
        terminal.getChildren().add(runButton);
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                addCommandHistory(terminalText.getText());
                terminalText.setText("");
            }
        });
        return terminal;
    }

    private void drawConfig(){
        VBox config = drawRightBox("Configuration");
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(config, 0, 0);
    }

    private void drawHistory(){
        myHistoryBox = drawRightBox("Command History");
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(myHistoryBox, 0, 1);
    }

    private void drawVariables(){
        VBox variables = drawRightBox("Current Variables");
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(variables, 0, 2);
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

    public void addCommandHistory(String allCommands){
        String[] commands = allCommands.split("\n");
        myCommandHistory.addAll(Arrays.asList(commands));
        drawHistory();
        for(int k = 4; k >= 0; k--){
            System.out.println(myCommandHistory.size() - k);
            if(myCommandHistory.size() - k > 0) {
                myHistoryBox.getChildren().add(new Text(myCommandHistory.get(myCommandHistory.size() - k - 1)));
            }
        }
    }

//    public void addVariable(String variable, String value){
//        myHistory.getChildren().add(new Text(variable + " " + value));
//    }

    public Scene getScene(){
        return myScene;
    }
}
