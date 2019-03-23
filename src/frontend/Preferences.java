package frontend;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.List;

/**
 * Serializable object that can hold window preferences to be loaded into a new window such as pen and background
 * color as well as language
 */
public class Preferences implements Serializable {
    static final List<String> dataFields = List.of(
            "PenColor",
            "BackgroundColor",
            "Language"
    );

    private String myPenColor;
    private String myBackgroundColor;
    private Language myLanguage;

    /**
     * Constructor that sets pen color, background color, and language
     * @param penColor is a Color corresponding to the desired saved color of the pen
     * @param backgroundColor is a Color corresponding to the desired saved color of the background
     * @param language is the Language enumeration corresponding to the desired user input language
     */
    public Preferences(Color penColor, Color backgroundColor, Language language){
        myPenColor = penColor.toString();
        myBackgroundColor = backgroundColor.toString();
        myLanguage = language;
    }

    /**
     * Getter for the pen color
     * @return the color of the pen saved in this Preferences object
     */
    public Color getPenColor(){
        return Color.valueOf(myPenColor);
    }

    /**
     * Getter for the background color
     * @return the color of the background saved in this Preferences object
     */
    public Color getBackgroundColor(){
        return Color.valueOf(myBackgroundColor);
    }

    /**
     * Getter for the language
     * @return the Language enumeration corresponding to the saved user input language
     */
    public Language getLanguage(){
        return myLanguage;
    }
}
