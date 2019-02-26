package frontend;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;

import static frontend.Language.ENGLISH;

/**
 * Stores the features of our simulation that should be able to be changed from a series of dropdowns and menus
 */
public class Configuration {
    private Color myBackgroundColor;
    private Image myTurtleImage;
    private String defaultPath = "data/DefaultTurtle.png";
    private Color myPenColor;
    private Language myLanguage;

    public Configuration(){
        myBackgroundColor = Color.WHITE;
        File turtleFile = new File(defaultPath);
        myTurtleImage = new Image(turtleFile.toURI().toString());
        myPenColor = Color.BLACK;
        myLanguage = ENGLISH;
    }

    public Image getTurtleImage(){
        return myTurtleImage;
    }

    public Color getBackgroundColor(){
        return myBackgroundColor;
    }

    public void setTurtleImage(Image turtleImage){
        myTurtleImage = turtleImage;
    }

    public void setBackgroundColor(Color color){
        myBackgroundColor = color;
    }

    public void setPenColor(Color color){
        myPenColor = color;
    }

    public void setLanguage(Language language){
        myLanguage = language;
    }

    public Language getLanguage(){
        return myLanguage;
    }
//    /**
//     * Get the current color of the turtle
//     * @return a paint color corresponding to the current color of the turtle
//     */
//    Color getTurtleColor();
//
//    /**
//     * Get the current color of the pen
//     * @return a paint color corresponding to the current color of the pen
//     */
//    Color getPenColor();
//
//    /**
//     * Reset the configuration to all of the defaults
//     */
//    void resetConfig();
//
//    /**
//     * Set the color of the turtle
//     * @param c is the paint color of the turtle
//     */
//    void setTurtleColor(Color c);
//
//    /**
//     * Set the color of the pen
//     * @param c is the paint color of the pen
//     */
//    void setPenColor(Color c);
//
//    /**
//     * Set the language that will be coded into to translate the commands
//     * @param s is the string representing the language
//     */
//    void setLanguage(String s);

}
