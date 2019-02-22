package frontend;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class View {
    private Scene myScene;
    private BorderPane myBorderPane;
    private GridPane myRightPane;
    private HBox myTitlePane;
    private VBox myHistory;


    private final int HEIGHT = 600;
    private final int WIDTH = 900;
    private final String STYLE_SHEET = "/GUIResources/ViewFormat.css";

    public View() {
        myTitlePane = new HBox();
        myBorderPane = new BorderPane();
        myScene = new Scene(myBorderPane, WIDTH, HEIGHT);
        myScene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
        myRightPane = new GridPane();
        myBorderPane.setTop(myTitlePane);
        myBorderPane.setRight(myRightPane);
        myBorderPane.setBottom(drawTerminal());
        myBorderPane.setCenter(drawCanvas());
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
        canvas.getChildren().add(new Text("Canvas"));
        canvas.setMaxHeight(450);
        canvas.setTranslateY(-2);
        canvas.setMaxWidth(640);
        canvas.setTranslateX(8);
        return canvas;
    }

    private Node drawTerminal(){
        HBox terminal = new HBox();
        terminal.getStyleClass().add("box-bot");
        terminal.getChildren().add(new Text("Terminal"));
        terminal.setMaxWidth(880);
        terminal.setTranslateX(10);
        terminal.setTranslateY(-10);
        return terminal;
    }

    private void drawConfig(){
        VBox config = drawRightBox("Configuration");
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(config, 0, 0);
    }

    private void drawHistory(){
        myHistory = drawRightBox("Command History");
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(myHistory, 0, 1);
    }

    private void drawVariables(){
        VBox variables = drawRightBox("Current Variables");
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(variables, 0, 2);
    }

    private VBox drawRightBox(String title){
        VBox rightBox = new VBox();
        rightBox.getStyleClass().add("box-right");
        Text text = new Text(title);
        rightBox.getChildren().add(text);
        rightBox.setAlignment(Pos.TOP_LEFT);
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

    public void addCommandHistory(String command){
        myHistory.getChildren().add(new Text(command));
    }

    public void addVariable(String variable, String value){
        myHistory.getChildren().add(new Text(variable + " " + value));
    }

    public Scene getScene(){
        return myScene;
    }
}
