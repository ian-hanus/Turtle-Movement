package frontend;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class Preferences implements Serializable {
    static final List<String> dataFields = List.of(
            "PenColor",
            "BackgroundColor",
            "Language"
    );

    private String myPenColor;
    private String myBackgroundColor;
    private Language myLanguage;

    public Preferences(Color penColor, Color backgroundColor, Language language){
        myPenColor = penColor.toString();
        myBackgroundColor = backgroundColor.toString();
        myLanguage = language;
    }

    public Color getPenColor(){
        return Color.valueOf(myPenColor);
    }

    public Color getBackgroundColor(){
        return Color.valueOf(myBackgroundColor);
    }

    public Language getLanguage(){
        return myLanguage;
    }
}
