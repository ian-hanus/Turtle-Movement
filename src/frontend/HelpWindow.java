package frontend;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelpWindow extends Stage {
private final String HELP_URL = "./src/GUIResources/helpInfo.html";

    public HelpWindow(){
        WebView webView = new WebView();
        StringBuilder sb = new StringBuilder();
        try {
            Files.lines(Paths.get(HELP_URL), StandardCharsets.UTF_8).forEach(sb::append);
        } catch (IOException e) {
            System.out.println("INVALID HELP URL");
        }
        webView.getEngine().loadContent(sb.toString());
        VBox root = new VBox();
        root.getChildren().add(webView);
        Scene scene = new Scene(root, 1200, 600);
        this.setTitle("Help");
        this.sizeToScene();
        this.setScene(scene);
        this.setResizable(false);
        this.show();
    }
}
