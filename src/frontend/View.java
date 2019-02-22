package frontend;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class View {
    private Scene myScene;
    private BorderPane myBorderPane;
    private GridPane myRightPane;

    private final int HEIGHT = 600;
    private final int WIDTH = 900;
    private final String STYLE_SHEET = "/GUIResources/ViewFormat.css";

    public View() {
        myBorderPane = new BorderPane();
        myScene = new Scene(myBorderPane, WIDTH, HEIGHT);
        myScene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
        myRightPane = new GridPane();
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
    }

    private Node drawCanvas(){
        HBox canvas = new HBox();
        canvas.getStyleClass().add("canvas");
        canvas.getChildren().add(new Text("Canvas"));
        canvas.setMaxHeight(450);
        canvas.setMaxWidth(630);
        return canvas;
    }

    private Node drawTerminal(){
        HBox terminal = new HBox();
        terminal.getStyleClass().add("box-bot");
        terminal.getChildren().add(new Text("Terminal"));
        terminal.setMaxWidth(880);
        terminal.setTranslateX(10);
        terminal.setTranslateY(10);
        return terminal;
    }

    private void drawConfig(){
        VBox config = drawRightBox("Configuration");
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(config, 0, 0);
    }

    private void drawHistory(){
        VBox history = drawRightBox("Command History");
        myRightPane.getStyleClass().add("pane-right");
        myRightPane.add(history, 0, 1);
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
        return rightBox;
    }

    public Scene getScene(){
        return myScene;
    }
}
